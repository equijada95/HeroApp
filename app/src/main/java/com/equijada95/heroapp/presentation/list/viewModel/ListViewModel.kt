package com.equijada95.heroapp.presentation.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equijada95.heroapp.R
import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.data.bbdd.models.HeroDbModel
import com.equijada95.heroapp.domain.repository.HeroRepository
import com.equijada95.heroapp.domain.result.ApiResult
import com.equijada95.heroapp.domain.utils.mapToDb
import com.equijada95.heroapp.presentation.error.UIError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    val state: StateFlow<State> get() = _state.asStateFlow()
    private val _state = MutableStateFlow(State())

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    private val originalHeroes = MutableStateFlow(emptyList<HeroModel>())

    init {
        viewModelScope.launch {
            getHeroes(this, false)
        }
    }

    fun setFav(hero: HeroModel) {
        if (!hero.isFavorite) { // funciona al revés porque ya se ha cambiado la variable fav del objeto
            deleteHero(hero.mapToDb())
        } else {
            insertHero(hero.mapToDb())
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(refreshing = true) }
            getHeroes(this, true)
        }
    }

    fun search(searchText: String) {
        _state.update { it.copy(searchText = searchText) }
        val searchHeros = originalHeroes.value.filter { hero ->
            hero.name.uppercase().contains(searchText.uppercase())
        }
        _state.update { it.copy(heroList = searchHeros) }
    }

    private suspend fun getHeroes(scope: CoroutineScope, refresh: Boolean) {
        repository.getHeroes(scope, refresh).onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    val heroes = result.data ?: emptyList()
                    handleSuccess(heroes)
                }
                is ApiResult.Error -> {
                    handleError(result)
                }
                is ApiResult.Loading -> {
                    _state.update { it.copy(loading = true) }
                }
            }
        }.launchIn(scope)
    }

    private fun handleSuccess(heroList: List<HeroModel>) {
        var heroes = heroList
        if (_state.value.searchText.isNotEmpty()) {
            heroes = heroes.filter { hero ->
                hero.name.uppercase().contains(_state.value.searchText.uppercase())
            }
        } else {
            originalHeroes.update { heroes }
        }
        _state.update {
            it.copy(
                heroList = heroes,
                loading = false,
                refreshing = false
            )
        }
    }

    private suspend fun handleError(result: ApiResult<List<HeroModel>>) {
        val apiError = result.error ?: return
        val error = when (apiError) {
            ApiResult.ApiError.SERVER_ERROR -> {
                UIError.Error(R.string.error_server)
            }
            ApiResult.ApiError.NO_CONNECTION_ERROR -> {
                UIError.Error(R.string.error_no_connection)
            }
            else -> {
                return
            }
        }
        val heroes = result.data ?: emptyList()
        originalHeroes.update { heroes }
        _events.send(Event.Error(error))
        _state.update {
            it.copy(
                heroList = heroes,
                loading = false,
                refreshing = false
            )
        }
    }

    private fun insertHero(hero: HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHero(hero)
        }
    }

    private fun deleteHero(hero: HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHero(hero)
        }
    }

    data class State (
        val heroList: List<HeroModel> = emptyList(),
        val loading: Boolean = false,
        val refreshing: Boolean = false,
        val searchText: String = "",
    )

    sealed interface Event {
        data class Error(val error: UIError = UIError.Error(R.string.error_server)) : Event
    }
}
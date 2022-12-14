package com.equijada95.heroapp.presentation.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.data.bbdd.models.HeroDbModel
import com.equijada95.heroapp.domain.repository.HeroRepository
import com.equijada95.heroapp.domain.result.ApiResult
import com.equijada95.heroapp.domain.utils.mapToDb
import com.equijada95.heroapp.presentation.list.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    val state: StateFlow<ListState> get() = _state.asStateFlow()

    private val _state = MutableStateFlow(ListState())

    private val originalHeroes = MutableStateFlow(emptyList<HeroModel>())

    private var searchText = MutableStateFlow("")

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
        this.searchText.update { searchText }
        val searchHeros = originalHeroes.value.filter { hero ->
            hero.name.uppercase().contains(searchText.uppercase())
        }
        _state.update { it.copy(heroList = searchHeros, error = ApiResult.ApiError.NO_ERROR) }
    }

    private suspend fun getHeroes(scope: CoroutineScope, refresh: Boolean) {
        repository.getHeroes(scope, refresh).onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    val heroes = result.data ?: emptyList()
                    success(heroes)
                }
                is ApiResult.Error -> {
                    if (result.error == ApiResult.ApiError.NO_ERROR) {
                        _state.update { it.copy(error = ApiResult.ApiError.NO_ERROR) }
                        return@onEach
                    }
                    val heroes = result.data ?: emptyList()
                    originalHeroes.update { heroes }
                    _state.update {
                        it.copy(
                            heroList = heroes,
                            loading = false,
                            error = result.error ?: ApiResult.ApiError.SERVER_ERROR,
                            refreshing = false
                        )
                    }
                }
                is ApiResult.Loading -> {
                    _state.update { it.copy(loading = true) }
                }
            }
        }.launchIn(scope)
    }

    private fun success(heroList: List<HeroModel>) {
        var heroes = heroList
        if (searchText.value.isNotEmpty()) {
            heroes = heroes.filter { hero ->
                hero.name.uppercase().contains(searchText.value.uppercase())
            }
        } else {
            originalHeroes.update { heroes }
        }
        _state.update {
            it.copy(
                heroList = heroes,
                loading = false,
                error = ApiResult.ApiError.NO_ERROR,
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

}
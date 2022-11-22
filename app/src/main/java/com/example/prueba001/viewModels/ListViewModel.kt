package com.example.prueba001.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.bbdd.repository.DataBaseRepository
import com.example.prueba001.model.HeroModel
import com.example.prueba001.repository.HeroRepository
import com.example.prueba001.utils.mapToModel
import com.example.prueba001.utils.setListWithFavorites
import com.example.prueba001.viewModels.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val dataBaseRepository: DataBaseRepository
) : ViewModel() {

    private val originalHeroes : MutableStateFlow<List<HeroModel>> = MutableStateFlow(emptyList())

    private val favoritesFlow = dataBaseRepository.getHeroesFromDataBase()

    private val coroutineScope = CoroutineScope(Job())

    private lateinit var favorites: StateFlow<List<HeroDbModel>>

    private val _state = MutableStateFlow(ListState())

    val state: StateFlow<ListState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            favorites = favoritesFlow.stateIn(scope = coroutineScope)
            getHeroes()
            favorites.collect {
                var heroes = _state.value.heroList
                heroes = setHeroesWithFavorites(heroes)
                _state.update { it.copy(heroList = it.heroList) }
                originalHeroes.update { heroes }
            }
        }
    }

    fun insertHero(hero: HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dataBaseRepository.insertHero(hero)
        }
    }

    fun deleteHero(hero: HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dataBaseRepository.deleteHero(hero)
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(refreshing = true) }
            getHeroes()
            _state.update { it.copy(refreshing = false) }
        }
    }

    fun search(search: String?) {
        search?.let { text ->
            val searchHeros = originalHeroes.value.filter { hero ->
                hero.name.uppercase().contains(text.uppercase())
            }
            _state.update { it.copy(heroList = searchHeros) }
        }
    }

    fun refreshSearch(search: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(refreshing = true) }
            getHeroes()
            search(search)
            _state.update { it.copy(refreshing = false) }
        }
    }

    private suspend fun getHeroes() {
        try {
            var heroes = heroRepository.getHeroes()

            heroes = setHeroesWithFavorites(heroes)
            _state.update { it.copy(heroList = heroes) }
            originalHeroes.update { heroes }
        } catch (_: SocketTimeoutException) { }
    }

    private fun setHeroesWithFavorites(heroList: List<HeroModel>): List<HeroModel> {
        if (heroList.isEmpty()) return favorites.value.mapToModel()
        else heroList.setListWithFavorites(favorites.value)
        return heroList
    }

}
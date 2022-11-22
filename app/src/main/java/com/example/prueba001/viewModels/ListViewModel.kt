package com.example.prueba001.viewModels

import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val dataBaseRepository: DataBaseRepository
) : ViewModel() {

    private val originalHeroes = MutableLiveData<List<HeroModel>>()

    private val favorites = dataBaseRepository.getHeroesFromDataBase()

    private val _state = MutableStateFlow(ListState())

    val state: StateFlow<ListState> get() = _state.asStateFlow()

    init {
        getHeroes()
        favorites.observeForever {
            var heroes = _state.value.heroList
            heroes = setHeroesWithFavorites(heroes)
            _state.update { it.copy(heroList = it.heroList) }
            originalHeroes.postValue(heroes)
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
            _getHeroes()
            _state.update { it.copy(refreshing = false) }
        }
    }

    fun search(search: String?) {
        search?.let { text ->
            val searchHeros = originalHeroes.value?.filter { hero ->
                hero.name.uppercase().contains(text.uppercase())
            }
            searchHeros?.let { _state.update { it.copy(heroList = searchHeros) } }
        }
    }

    fun refreshSearch(search: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(refreshing = true) }
            _getHeroes()
            search(search)
            _state.update { it.copy(refreshing = false) }
        }
    }

    private fun getHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _getHeroes()
        }
    }

    private suspend fun _getHeroes() {
        try {
            var heroes = heroRepository.getHeroes()

            heroes = setHeroesWithFavorites(heroes)
            _state.update { it.copy(heroList = heroes) }
            originalHeroes.postValue(heroes)
        } catch (_: SocketTimeoutException) { }
    }

    private fun setHeroesWithFavorites(heroList: List<HeroModel>): List<HeroModel> {
        favorites.value?.let { favs ->
            if (heroList.isEmpty()) return favs.mapToModel()
            else heroList.setListWithFavorites(favs)
        }
        return heroList
    }

}
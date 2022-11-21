package com.example.prueba001.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.bbdd.repository.DataBaseRepository
import com.example.prueba001.model.HeroModel
import com.example.prueba001.repository.HeroRepository
import com.example.prueba001.utils.mapToModel
import com.example.prueba001.utils.setListWithFavorites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val dataBaseRepository: DataBaseRepository
) : ViewModel() {

    private val _heroes = MutableLiveData<List<HeroModel>>()

    private val originalHeroes = MutableLiveData<List<HeroModel>>()

    val heroes: LiveData<List<HeroModel>>
        get() = _heroes

    private val favorites = dataBaseRepository.getHeroesFromDataBase()

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        getHeroes()
        favorites.observeForever {
            var heroes = _heroes.value ?: emptyList()
            it?.let { favorites ->
                if (heroes.isEmpty()) heroes = favorites.mapToModel()
                else heroes.setListWithFavorites(favorites)
            }
            _heroes.postValue(heroes)
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
            _isRefreshing.emit(true)
            _getHeroes()
            _isRefreshing.emit(false)
        }
    }

    fun search(search: String?) {
        search?.let { text ->
            val searchHeros = originalHeroes.value?.filter { hero ->
                hero.name.uppercase().contains(text.uppercase())
            }
            _heroes.postValue(searchHeros)
        }
    }

    fun refreshSearch(search: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.emit(true)
            _getHeroes()
            search(search)
            _isRefreshing.emit(false)
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

            favorites.value?.let { favs ->
                heroes.setListWithFavorites(favs)
            }
            _heroes.postValue(heroes)
            originalHeroes.postValue(heroes)
        } catch (_: SocketTimeoutException) {
            _heroes.postValue(favorites.value?.mapToModel())
        }
    }

}
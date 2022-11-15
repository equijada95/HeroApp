package com.example.prueba001.bbdd.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.bbdd.repository.DataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataBaseViewModel @Inject constructor(
    private val repository: DataBaseRepository
) : ViewModel() {
    
    var _favorites = MutableLiveData<List<HeroDbModel>>()

    init {
        getAllFavs()
    }

    fun getAllFavs() : LiveData<List<HeroDbModel>> {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repository.getHeroesFromDataBase()
            _favorites.postValue(favorites)
        }
        return _favorites
    }

    fun insertHero(hero: HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHero(hero)
        }
    }

    fun deleteHero(hero: HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHero(hero)
        }
    }

}
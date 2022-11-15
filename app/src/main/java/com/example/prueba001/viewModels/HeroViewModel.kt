package com.example.prueba001.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba001.model.HeroModel
import com.example.prueba001.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    private val _heroes = MutableLiveData<List<HeroModel>>()

    fun getHeroes(): LiveData<List<HeroModel>> {
        viewModelScope.launch(Dispatchers.IO) {
            val heroes = repository.getHeroes()
            _heroes.postValue(heroes)
        }
        return _heroes
    }

}
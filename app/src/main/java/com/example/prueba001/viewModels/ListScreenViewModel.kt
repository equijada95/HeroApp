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
class ListScreenViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    private val _heros = MutableLiveData<List<HeroModel>>()

    fun getHeros(): LiveData<List<HeroModel>> {
        viewModelScope.launch(Dispatchers.IO) {
            val heros = repository.getHeros()
            _heros.postValue(heros)
        }
        return _heros
    }

}
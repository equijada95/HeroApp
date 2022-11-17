package com.example.prueba001.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba001.model.HeroModel
import com.example.prueba001.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    private val _heroes = MutableLiveData<List<HeroModel>>()

    private val originalHeroes = MutableLiveData<List<HeroModel>>()

    val heroes: LiveData<List<HeroModel>>
        get() = _heroes

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        getHeroes()
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
                hero.name.contains(text)
            }
            _heroes.postValue(searchHeros)
        }
    }

    private fun getHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _getHeroes()
        }
    }

    private suspend fun _getHeroes() {
        try {
            val heroes = repository.getHeroes()
            _heroes.postValue(heroes)
            originalHeroes.postValue(heroes)
        } catch (_: SocketTimeoutException) { }
    }

}
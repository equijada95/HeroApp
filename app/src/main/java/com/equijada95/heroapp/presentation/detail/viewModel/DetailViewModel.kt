package com.equijada95.heroapp.presentation.detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepository
import com.equijada95.heroapp.domain.utils.mapToDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepository
) : ViewModel() {

    fun setFav(hero: com.equijada95.heroapp.data.api.model.HeroModel) {
        if (hero.isFavorite) {
            deleteHero(hero.mapToDb())
        } else {
            insertHero(hero.mapToDb())
        }
    }

    private fun insertHero(hero: com.equijada95.heroapp.data.bbdd.models.HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHero(hero)
        }
    }

    private fun deleteHero(hero: com.equijada95.heroapp.data.bbdd.models.HeroDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHero(hero)
        }
    }

}
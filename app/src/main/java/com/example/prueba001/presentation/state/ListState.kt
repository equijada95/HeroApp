package com.example.prueba001.presentation.state

import com.example.prueba001.domain.api.model.HeroModel

data class ListState (
    val heroList: List<HeroModel> = emptyList(),
    val refreshing: Boolean = false
)

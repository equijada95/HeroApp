package com.equijada95.heroapp.presentation.list.state

import com.equijada95.heroapp.data.api.model.HeroModel

data class ListState (
    val heroList: List<HeroModel> = emptyList(),
    val loading: Boolean = false,
    val refreshing: Boolean = false
)
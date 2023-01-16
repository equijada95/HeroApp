package com.equijada95.heroapp.presentation.list.state

import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.domain.result.ApiResult

data class ListState (
    val heroList: List<HeroModel> = emptyList(),
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val searchText: String = "",
    val error: ApiResult.ApiError = ApiResult.ApiError.NO_ERROR
)
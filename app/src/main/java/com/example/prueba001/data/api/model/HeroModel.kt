package com.example.prueba001.data.api.model

import com.example.prueba001.data.api.model.appearance.AppearanceModel
import com.example.prueba001.data.api.model.biography.BiographyModel
import com.example.prueba001.data.api.model.images.ImagesModel
import com.example.prueba001.data.api.model.stats.StatsModel

data class HeroModel(
    var id: Int,
    var images: ImagesModel?,
    var name: String,
    var powerstats: StatsModel?,
    var appearance: AppearanceModel?,
    var biography: BiographyModel?,
    var isFavorite: Boolean = false
)

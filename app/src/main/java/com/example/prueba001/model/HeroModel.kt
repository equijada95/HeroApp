package com.example.prueba001.model

import com.example.prueba001.model.appearance.AppearanceModel
import com.example.prueba001.model.biography.BiographyModel
import com.example.prueba001.model.images.ImagesModel
import com.example.prueba001.model.stats.StatsModel

data class HeroModel(
    var images: ImagesModel,
    var name: String,
    var powerstats: StatsModel,
    var appearance: AppearanceModel,
    var biography: BiographyModel
)

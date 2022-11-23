package com.equijada95.heroapp.data.api.model

import com.equijada95.heroapp.data.api.model.appearance.AppearanceModel
import com.equijada95.heroapp.data.api.model.biography.BiographyModel
import com.equijada95.heroapp.data.api.model.images.ImagesModel
import com.equijada95.heroapp.data.api.model.stats.StatsModel

data class HeroModel(
    var id: Int,
    var images: ImagesModel?,
    var name: String,
    var powerstats: StatsModel?,
    var appearance: AppearanceModel?,
    var biography: BiographyModel?,
    var isFavorite: Boolean = false
)

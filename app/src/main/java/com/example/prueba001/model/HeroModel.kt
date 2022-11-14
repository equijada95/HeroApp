package com.example.prueba001.model

import com.example.prueba001.model.appearance.AppearanceModel
import com.example.prueba001.model.biography.BiographyModel
import com.example.prueba001.model.images.ImagesModel
import com.example.prueba001.model.stats.StatsModel

data class HeroModel(
    var id: Int,
    var images: ImagesModel?,
    var name: String,
    var powerstats: StatsModel?,
    var appearance: AppearanceModel?,
    var biography: BiographyModel?,
    var isFavorite: Boolean = false
) {
    companion object {
        @JvmStatic
        fun heroTest() = HeroModel(
            1,
            ImagesModel.imagesTest(),
            "A-Bomb",
            StatsModel.statsTest(),
            AppearanceModel.appearanceTest(),
            BiographyModel.biographyTest(),
            false
        )
    }
}

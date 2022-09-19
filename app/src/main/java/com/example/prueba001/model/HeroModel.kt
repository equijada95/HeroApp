package com.example.prueba001.model

import com.example.prueba001.bbdd.models.HeroDbModel
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
        fun generateModel(model: HeroDbModel?) : HeroModel? {
            model?.let {
                return HeroModel(
                    it.id,
                    ImagesModel.generateModel(it.images),
                    it.name,
                    StatsModel.generateModel(it.powerstats),
                    AppearanceModel.generateModel(it.appearance),
                    BiographyModel.generateModel(it.biography),
                    it.isFavorite
                )
            }
            return null
        }

        fun mapList(elements: List<HeroDbModel?>?): List<HeroModel?>? {
            val returned = mutableListOf<HeroModel?>()
            elements?.let {
                for (element in it) {
                    returned.add(generateModel(element))
                }
            }
            return returned
        }

    }

}

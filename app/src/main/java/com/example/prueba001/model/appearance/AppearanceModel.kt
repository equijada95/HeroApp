package com.example.prueba001.model.appearance

import com.example.prueba001.bbdd.models.appearance.AppearanceDbModel

data class AppearanceModel(
    var gender: String,
    var race: String,
    var height: List<String>,
    var weight: List<String>,
    var eyeColor: String,
    var hairColor: String
) {

    companion object {
        @JvmStatic
        fun generateModel(model: AppearanceDbModel?) : AppearanceModel? {
            model?.let {
                return AppearanceModel(
                    it.gender,
                    it.race,
                    it.height,
                    it.weight,
                    it.eyeColor,
                    it.hairColor
                )
            }
            return null
        }
    }

}

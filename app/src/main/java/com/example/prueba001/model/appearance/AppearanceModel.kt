package com.example.prueba001.model.appearance

import com.example.prueba001.bbdd.models.appearance.AppearanceDbModel
import com.example.prueba001.utils.getList

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
            return model?.let {
                AppearanceModel(
                    it.gender,
                    it.race,
                    it.height.getList(),
                    it.weight.getList(),
                    it.eyeColor,
                    it.hairColor
                )
            }
        }
    }

}

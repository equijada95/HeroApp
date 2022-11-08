package com.example.prueba001.model.biography

import com.example.prueba001.bbdd.models.biography.BiographyDbModel
import com.example.prueba001.utils.StringUtils

data class BiographyModel(
    var fullName: String,
    var alterEgos: String,
    var aliases: List<String>,
    var placeOfBirth: String,
    var firstAppearance: String,
    var publisher: String,
    var alignment: String
) {

    companion object {
        @JvmStatic
        fun generateModel(model: BiographyDbModel?): BiographyModel? {
            return model?.let {
                BiographyModel(
                    it.fullName,
                    it.alterEgos,
                    StringUtils.getListFromString(it.aliases),
                    it.placeOfBirth,
                    it.firstAppearance,
                    it.publisher,
                    it.alignment
                )
            }
        }
    }
}

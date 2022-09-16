package com.example.prueba001.model.biography

import com.example.prueba001.bbdd.models.biography.BiographyDbModel

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
        fun generateModel(model: BiographyDbModel?) : BiographyModel? {
            model?.let {
                return BiographyModel(
                    it.fullName,
                    it.alterEgos,
                    it.aliases,
                    it.placeOfBirth,
                    it.firstAppearance,
                    it.publisher,
                    it.alignment
                )
            }
            return null
        }
    }

}

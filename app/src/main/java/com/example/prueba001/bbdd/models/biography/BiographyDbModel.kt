package com.example.prueba001.bbdd.models.biography

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.model.biography.BiographyModel

@Entity
data class BiographyDbModel(
    @PrimaryKey val idBiography: Int,
    @ColumnInfo val fullName: String,
    @ColumnInfo val alterEgos: String,
    @ColumnInfo val aliases: String,
    @ColumnInfo val placeOfBirth: String,
    @ColumnInfo val firstAppearance: String,
    @ColumnInfo val publisher: String,
    @ColumnInfo val alignment: String
) {
    companion object {
        @JvmStatic
        fun generateModel(model: BiographyModel?) : BiographyDbModel? {
            return model?.let {
                BiographyDbModel(
                    Math.random().toInt(),
                    it.fullName,
                    it.alterEgos,
                    it.aliases.toString(),
                    it.placeOfBirth,
                    it.firstAppearance,
                    it.publisher,
                    it.alignment
                )
            }
        }
    }
}

package com.example.prueba001.bbdd.models.biography

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.model.biography.BiographyModel

@Entity
data class BiographyDbModel(
    @PrimaryKey val id: Int,
    @ColumnInfo val fullName: String,
    @ColumnInfo val alterEgos: String,
    @ColumnInfo val aliases: List<String>,
    @ColumnInfo val placeOfBirth: String,
    @ColumnInfo val firstAppearance: String,
    @ColumnInfo val publisher: String,
    @ColumnInfo val alignment: String
) {
    companion object {
        @JvmStatic
        fun generateModel(model: BiographyModel?) : BiographyDbModel? {
            model?.let {
                return BiographyDbModel(
                    Math.random().toInt(),
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

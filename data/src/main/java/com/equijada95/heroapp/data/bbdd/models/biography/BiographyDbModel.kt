package com.equijada95.heroapp.data.bbdd.models.biography

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)

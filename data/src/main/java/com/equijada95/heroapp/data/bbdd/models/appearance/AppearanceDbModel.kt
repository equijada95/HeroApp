package com.equijada95.heroapp.data.bbdd.models.appearance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppearanceDbModel(
    @PrimaryKey val idAppearance: Int,
    @ColumnInfo val gender: String,
    @ColumnInfo val race: String,
    @ColumnInfo val height: String,
    @ColumnInfo val weight: String,
    @ColumnInfo val eyeColor: String,
    @ColumnInfo val hairColor: String
)

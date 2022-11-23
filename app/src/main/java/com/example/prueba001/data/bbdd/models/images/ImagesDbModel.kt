package com.example.prueba001.data.bbdd.models.images

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImagesDbModel(
    @PrimaryKey val idImages: Int,
    @ColumnInfo val xs : String,
    @ColumnInfo val sm : String,
    @ColumnInfo val md: String,
    @ColumnInfo val lg: String
)

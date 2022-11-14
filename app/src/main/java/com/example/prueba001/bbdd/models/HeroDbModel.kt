package com.example.prueba001.bbdd.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.bbdd.models.appearance.AppearanceDbModel
import com.example.prueba001.bbdd.models.biography.BiographyDbModel
import com.example.prueba001.bbdd.models.images.ImagesDbModel
import com.example.prueba001.bbdd.models.stats.StatsDbModel

@Entity(tableName = "hero_list")
data class HeroDbModel(
    @PrimaryKey val id: Int,
    @Embedded val images: ImagesDbModel?,
    @ColumnInfo val name: String,
    @Embedded val powerstats: StatsDbModel?,
    @Embedded val appearance: AppearanceDbModel?,
    @Embedded val biography: BiographyDbModel?,
    @ColumnInfo val isFavorite: Boolean = false
)

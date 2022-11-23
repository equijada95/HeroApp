package com.equijada95.heroapp.data.bbdd.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.equijada95.heroapp.data.bbdd.models.appearance.AppearanceDbModel
import com.equijada95.heroapp.data.bbdd.models.biography.BiographyDbModel
import com.equijada95.heroapp.data.bbdd.models.images.ImagesDbModel
import com.equijada95.heroapp.data.bbdd.models.stats.StatsDbModel

@Entity(tableName = "hero_list")
data class HeroDbModel(
    @PrimaryKey val id: Int,
    @Embedded val images: ImagesDbModel?,
    @ColumnInfo val name: String,
    @Embedded val powerstats: StatsDbModel?,
    @Embedded val appearance: AppearanceDbModel?,
    @Embedded val biography: BiographyDbModel?
)

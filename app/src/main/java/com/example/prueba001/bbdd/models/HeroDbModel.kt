package com.example.prueba001.bbdd.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.bbdd.models.appearance.AppearanceDbModel
import com.example.prueba001.bbdd.models.biography.BiographyDbModel
import com.example.prueba001.bbdd.models.images.ImagesDbModel
import com.example.prueba001.bbdd.models.stats.StatsDbModel
import com.example.prueba001.model.HeroModel

@Entity(tableName = "hero_list")
data class HeroDbModel(
    @PrimaryKey val id: Int,
    @Embedded val images: ImagesDbModel?,
    @ColumnInfo val name: String,
    @Embedded val powerstats: StatsDbModel?,
    @Embedded val appearance: AppearanceDbModel?,
    @Embedded val biography: BiographyDbModel?,
    @ColumnInfo val isFavorite: Boolean = false
) {

    companion object {
        @JvmStatic
        fun generateModel(model: HeroModel?) : HeroDbModel? {
            return model?.let {
                HeroDbModel(
                    it.id,
                    ImagesDbModel.generateModel(it.images),
                    it.name,
                    StatsDbModel.generateModel(it.powerstats),
                    AppearanceDbModel.generateModel(it.appearance),
                    BiographyDbModel.generateModel(it.biography),
                    it.isFavorite
                )
            }
        }
    }

}

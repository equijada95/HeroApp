package com.example.prueba001.bbdd.models.appearance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.model.appearance.AppearanceModel

@Entity
data class AppearanceDbModel(
    @PrimaryKey val id: Int,
    @ColumnInfo val gender: String,
    @ColumnInfo val race: String,
    @ColumnInfo val height: List<String>,
    @ColumnInfo val weight: List<String>,
    @ColumnInfo val eyeColor: String,
    @ColumnInfo val hairColor: String
) {

    companion object {
        @JvmStatic
        fun generateModel(model: AppearanceModel?) : AppearanceDbModel? {
            model?.let {
                return AppearanceDbModel(
                    Math.random().toInt(),
                    it.gender,
                    it.race,
                    it.height,
                    it.weight,
                    it.eyeColor,
                    it.hairColor
                )
            }
            return null
        }
    }

}

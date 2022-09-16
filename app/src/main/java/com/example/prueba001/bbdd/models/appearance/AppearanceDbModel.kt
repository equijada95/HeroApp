package com.example.prueba001.bbdd.models.appearance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.model.appearance.AppearanceModel

@Entity
data class AppearanceDbModel(
    @PrimaryKey val idAppearance: Int,
    @ColumnInfo val gender: String,
    @ColumnInfo val race: String,
    @ColumnInfo val height: String,
    @ColumnInfo val weight: String,
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
                    it.height.toString(),
                    it.weight.toString(),
                    it.eyeColor,
                    it.hairColor
                )
            }
            return null
        }
    }

}

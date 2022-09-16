package com.example.prueba001.bbdd.models.images

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.model.images.ImagesModel

@Entity
data class ImagesDbModel(
    @PrimaryKey val id: Int,
    @ColumnInfo val xs : String,
    @ColumnInfo val sm : String,
    @ColumnInfo val md: String,
    @ColumnInfo val lg: String
) {

    companion object {
        @JvmStatic
        fun generateModel(model: ImagesModel?) : ImagesDbModel? {
            model?.let {
                return ImagesDbModel(
                    Math.random().toInt(),
                    it.xs,
                    it.sm,
                    it.md,
                    it.lg
                )
            }
            return null
        }
    }

}

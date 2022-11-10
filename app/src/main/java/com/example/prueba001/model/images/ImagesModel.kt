package com.example.prueba001.model.images

import com.example.prueba001.bbdd.models.images.ImagesDbModel

data class ImagesModel(
    var xs : String,
    var sm : String,
    var md: String,
    var lg: String
) {

    companion object {
        @JvmStatic
        fun generateModel(model: ImagesDbModel?) : ImagesModel? {
            return model?.let {
                ImagesModel(
                    it.xs,
                    it.sm,
                    it.md,
                    it.lg
                )
            }
        }
    }

}

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

        @JvmStatic
        fun imagesTest() = ImagesModel(
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/xs/1-a-bomb.jpg",
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/1-a-bomb.jpg",
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/md/1-a-bomb.jpg",
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/1-a-bomb.jpg"
        )
    }

}

package com.example.prueba001.utils

import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.model.HeroModel

fun String.getList(): List<String> {
    val firstReplace = replace("[", "")
    val secondReplace = firstReplace.replace("]", "")
    return listOf(*secondReplace.split(",").toTypedArray())
}

fun List<HeroModel>.getHeroFromFavorites(favorites: List<HeroDbModel>, predicate: (hero: HeroModel) -> Unit) {
    forEach { it.isFavorite = false }
    favorites.forEach { heroDbModel ->
        find { hero -> heroDbModel.id == hero.id }?.let { favorite ->
            predicate(favorite)
        }
    }
}
package com.example.prueba001.utils

import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.model.HeroModel
import java.util.ArrayList
import java.util.Arrays

fun String.getList(): List<String> {
    val firstReplace = replace("[", "")
    val secondReplace = firstReplace.replace("]", "")
    return listOf(*secondReplace.split(",").toTypedArray())
}

fun List<HeroModel>.getHeroFromFavorites(favorites: List<HeroDbModel>, predicate: (hero: HeroModel) -> Unit) {
    for (heroModel in this) {
        for (heroDbModel in favorites) {
            if (heroModel.id == heroDbModel.id) {
                predicate(heroModel)
            }
        }
    }
}
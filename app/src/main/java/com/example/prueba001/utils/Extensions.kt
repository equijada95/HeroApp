package com.example.prueba001.utils

import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.model.HeroModel
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder


fun String.getList(): List<String> {
    val firstReplace = replace("[", "")
    val secondReplace = firstReplace.replace("]", "")
    return listOf(*secondReplace.split(",").toTypedArray())
}

fun List<HeroModel>.getHeroFromFavorites(favorites: List<HeroDbModel>, predicate: (hero: HeroModel) -> Unit) {
    favorites.forEach { heroDbModel ->
        find { hero -> heroDbModel.id == hero.id }?.let { favorite ->
            predicate(favorite)
        }
    }
}

fun List<HeroModel>.setAllFavoritesFalse() {
    forEach { it.isFavorite = false }
}

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <T> T.toJson(): String {
    return Gson().toJson(this)
}

fun String.encode(): String? {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.decode(): String? {
    return URLDecoder.decode(this, "UTF-8")
}
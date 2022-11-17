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


// esta funcion convierte cualquier objeto en un json y lo encripta para que
// pueda parsearse correctamente para la navegacion
fun <T> T.encode(): String? {
    val json = Gson().toJson(this)
    return URLEncoder.encode(json, "UTF-8")
}

// esta funcion recoge el objeto encodado, lo desencripta y lo convierte en el objeto indicado en el type
fun <A> String.decode(type: Class<A>): A {
    val decoded = URLDecoder.decode(this, "UTF-8")
    return Gson().fromJson(decoded, type)
}
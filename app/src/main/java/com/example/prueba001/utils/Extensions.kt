package com.example.prueba001.utils

import android.util.Base64
import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.model.HeroModel
import com.google.gson.Gson
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


const val secretKey = "tK5UTui+DPh8lIlBxya5XVsmeDCoUl6vHhdIESMB6sQ="
const val salt = "QWlGNHNhMTJTQWZ2bGhpV3U=" // base64 decode => AiF4sa12SAfvlhiWu
const val iv = "bVQzNFNhRkQ1Njc4UUFaWA==" // base64 decode => mT34SaFD5678QAZX

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

fun String.encrypt() :  String? {
    try {
        val ivParameterSpec = IvParameterSpec(Base64.decode(iv, Base64.DEFAULT))

        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val spec =  PBEKeySpec(secretKey.toCharArray(), Base64.decode(salt, Base64.DEFAULT), 10000, 256)
        val tmp = factory.generateSecret(spec)
        val secretKey =  SecretKeySpec(tmp.encoded, "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
        return Base64.encodeToString(cipher.doFinal(this.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
    }
    catch (e: Exception) {
        println("Error while encrypting: $e")
    }
    return null
}

fun String.decrypt() : String? {
    try {

        val ivParameterSpec =  IvParameterSpec(Base64.decode(iv, Base64.DEFAULT))

        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val spec =  PBEKeySpec(secretKey.toCharArray(), Base64.decode(salt, Base64.DEFAULT), 10000, 256)
        val tmp = factory.generateSecret(spec);
        val secretKey =  SecretKeySpec(tmp.encoded, "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        return  String(cipher.doFinal(Base64.decode(this, Base64.DEFAULT)))
    }
    catch (e : Exception) {
        println("Error while decrypting: $e");
    }
    return null
}
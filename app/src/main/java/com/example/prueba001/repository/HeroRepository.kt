package com.example.prueba001.repository

import com.example.prueba001.model.HeroModel
import com.example.prueba001.provider.HeroProvider
import java.net.UnknownHostException
import javax.inject.Inject

interface HeroRepository {
    suspend fun getHeroes(): List<HeroModel>
}

class HeroRepositoryImpl @Inject constructor(
    private val heroProvider : HeroProvider
) : HeroRepository {

    private var heroes: List<HeroModel> = emptyList()

    override suspend fun getHeroes(): List<HeroModel> {
        try {
            val apiResponse = heroProvider.getAll().body()
            heroes = apiResponse ?: emptyList()
            return heroes
        } catch(e: UnknownHostException) {
            return emptyList()
        }
    }


}
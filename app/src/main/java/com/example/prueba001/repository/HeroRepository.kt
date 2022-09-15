package com.example.prueba001.repository

import com.example.prueba001.model.HeroModel
import com.example.prueba001.provider.HeroProvider
import java.net.UnknownHostException
import javax.inject.Inject

interface HeroRepository {
    suspend fun getHeros(): List<HeroModel>
}

class HeroRepositoryImpl @Inject constructor(
    private val heroProvider : HeroProvider
) : HeroRepository {

    private var heros: List<HeroModel> = emptyList()

    override suspend fun getHeros(): List<HeroModel> {
        try {
            val apiResponse = heroProvider.getAll().body()
            heros = apiResponse ?: emptyList()
            return heros
        } catch(e: UnknownHostException) {
            return emptyList()
        }
    }


}
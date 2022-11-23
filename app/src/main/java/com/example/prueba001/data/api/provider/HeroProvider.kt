package com.example.prueba001.data.api.provider

import com.example.prueba001.domain.api.model.HeroModel
import retrofit2.Response
import retrofit2.http.GET

interface HeroProvider {

    @GET("all.json")
    suspend fun getAll(): Response<List<HeroModel>>

}
package com.equijada95.heroapp.data.api.provider

import com.equijada95.heroapp.data.api.model.HeroModel
import retrofit2.Response
import retrofit2.http.GET

interface HeroProvider {

    @GET("all.json")
    suspend fun getAll(): Response<List<HeroModel>>

}
package com.example.prueba001.bbdd.repository

import androidx.lifecycle.LiveData
import com.example.prueba001.bbdd.dao.HeroDao
import com.example.prueba001.bbdd.models.HeroDbModel
import javax.inject.Inject

interface DataBaseRepository {

    suspend fun getHeroesFromDataBase(): List<HeroDbModel>
    suspend fun insertHero(hero: HeroDbModel)
    suspend fun deleteHero(hero: HeroDbModel)

}

class DataBaseRepositoryImpl @Inject constructor(
    private val dao: HeroDao
) : DataBaseRepository {

    override suspend  fun getHeroesFromDataBase(): List<HeroDbModel> = dao.getAll()

    override suspend fun insertHero(hero: HeroDbModel) {
        dao.insert(hero)
    }

    override suspend fun deleteHero(hero: HeroDbModel) {
        dao.delete(hero)
    }

}
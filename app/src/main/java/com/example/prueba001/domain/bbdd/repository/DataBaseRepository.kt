package com.example.prueba001.domain.bbdd.repository

import com.example.prueba001.data.bbdd.dao.HeroDao
import com.example.prueba001.data.bbdd.models.HeroDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DataBaseRepository {

    fun getHeroesFromDataBase(): Flow<List<HeroDbModel>>
    suspend fun insertHero(hero: HeroDbModel)
    suspend fun deleteHero(hero: HeroDbModel)

}

class DataBaseRepositoryImpl @Inject constructor(
    private val dao: HeroDao
) : DataBaseRepository {

    override fun getHeroesFromDataBase(): Flow<List<HeroDbModel>> = dao.getAll()

    override suspend fun insertHero(hero: HeroDbModel) {
        dao.insert(hero)
    }

    override suspend fun deleteHero(hero: HeroDbModel) {
        dao.delete(hero)
    }

}
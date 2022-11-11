package com.example.prueba001.modules

import android.app.Application
import com.example.prueba001.bbdd.dao.HeroDao
import com.example.prueba001.bbdd.dataBase.HeroDataBase
import com.example.prueba001.bbdd.repository.DataBaseRepository
import com.example.prueba001.bbdd.repository.DataBaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesHeroDataBase(context: Application): HeroDataBase {
        return HeroDataBase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesDataBaseRepository(dataBaseRepositoryImpl: DataBaseRepositoryImpl): DataBaseRepository {
        return dataBaseRepositoryImpl
    }

    @Provides
    @Singleton
    fun providesHeroDao(heroDataBase: HeroDataBase): HeroDao {
        return heroDataBase.getHeroDao()
    }
}
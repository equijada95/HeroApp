package com.equijada95.heroapp.modules

import android.app.Application
import com.equijada95.heroapp.data.bbdd.dao.HeroDao
import com.equijada95.heroapp.data.bbdd.dataBase.HeroDataBase
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepository
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepositoryImpl
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
package com.equijada95.heroapp.modules

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
    fun providesDataBaseRepository(dataBaseRepositoryImpl: DataBaseRepositoryImpl): DataBaseRepository {
        return dataBaseRepositoryImpl
    }
}
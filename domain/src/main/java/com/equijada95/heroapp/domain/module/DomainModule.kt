package com.equijada95.heroapp.domain.module

import com.equijada95.heroapp.data.api.provider.HeroProvider
import com.equijada95.heroapp.domain.api.repository.HeroRepository
import com.equijada95.heroapp.domain.api.repository.HeroRepositoryImpl
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepository
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun providesDataBaseRepository(dataBaseRepositoryImpl: DataBaseRepositoryImpl) = dataBaseRepositoryImpl


    @Provides
    @Singleton
    fun providerHeroRepository(provider: HeroProvider): HeroRepository = HeroRepositoryImpl(provider)

}
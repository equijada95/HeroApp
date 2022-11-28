package com.equijada95.heroapp.domain.module

import com.equijada95.heroapp.domain.repository.HeroRepository
import com.equijada95.heroapp.domain.repository.HeroRepositoryImpl
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
    fun providerHeroRepository(heroRepositoryImpl: HeroRepositoryImpl): HeroRepository = heroRepositoryImpl

}
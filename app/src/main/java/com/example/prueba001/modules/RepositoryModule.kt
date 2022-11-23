package com.example.prueba001.modules

import com.example.prueba001.data.api.provider.HeroProvider
import com.example.prueba001.domain.api.repository.HeroRepository
import com.example.prueba001.domain.api.repository.HeroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerHeroRepository(provider: HeroProvider): HeroRepository =
        HeroRepositoryImpl(provider)

}
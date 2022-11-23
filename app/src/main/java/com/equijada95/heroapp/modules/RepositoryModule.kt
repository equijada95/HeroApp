package com.equijada95.heroapp.modules

import com.equijada95.heroapp.data.api.provider.HeroProvider
import com.equijada95.heroapp.domain.api.repository.HeroRepository
import com.equijada95.heroapp.domain.api.repository.HeroRepositoryImpl
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
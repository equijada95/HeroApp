package com.equijada95.heroapp.domain.module

import android.app.Application
import com.equijada95.heroapp.data.api.provider.HeroProvider
import com.equijada95.heroapp.data.bbdd.dao.HeroDao
import com.equijada95.heroapp.data.bbdd.dataBase.HeroDataBase
import com.equijada95.heroapp.domain.api.repository.HeroRepository
import com.equijada95.heroapp.domain.api.repository.HeroRepositoryImpl
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepository
import com.equijada95.heroapp.domain.bbdd.repository.DataBaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun providesDataBaseRepository(dataBaseRepositoryImpl: DataBaseRepositoryImpl): DataBaseRepository {
        return dataBaseRepositoryImpl
    }

    @Provides
    @Singleton
    fun providerHeroRepository(provider: HeroProvider): HeroRepository =
        HeroRepositoryImpl(provider)

}
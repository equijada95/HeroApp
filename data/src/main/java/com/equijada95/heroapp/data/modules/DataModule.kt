package com.equijada95.heroapp.data.modules

import android.app.Application
import com.equijada95.heroapp.data.bbdd.dao.HeroDao
import com.equijada95.heroapp.data.bbdd.dataBase.HeroDataBase
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
class DataModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://akabab.github.io/superhero-api/api/".toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providerHeroProvider(retrofit: Retrofit): com.equijada95.heroapp.data.api.provider.HeroProvider =
        retrofit.create(com.equijada95.heroapp.data.api.provider.HeroProvider::class.java)

    @Provides
    @Singleton
    fun providesHeroDao(heroDataBase: HeroDataBase): HeroDao {
        return heroDataBase.getHeroDao()
    }

    @Provides
    @Singleton
    fun providesHeroDataBase(context: Application): HeroDataBase {
        return HeroDataBase.getDatabase(context)
    }

}
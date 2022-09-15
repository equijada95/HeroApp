package com.example.prueba001.modules;

import android.app.Application;

import com.example.prueba001.bbdd.dao.HeroDao;
import com.example.prueba001.bbdd.dataBase.HeroDataBase;
import com.example.prueba001.bbdd.repository.DataBaseRepository;
import com.example.prueba001.bbdd.repository.DataBaseRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public HeroDataBase providesHeroDataBase(Application context) {
        return HeroDataBase.Companion.getDatabase(context);
    }

    @Provides
    @Singleton
    public DataBaseRepository providesDataBaseRepository(DataBaseRepositoryImpl dataBaseRepositoryImpl) {
        return dataBaseRepositoryImpl;
    }

    @Provides
    @Singleton
    public HeroDao providesHeroDao(HeroDataBase heroDataBase) {
        return heroDataBase.getHeroDao();
    }

}

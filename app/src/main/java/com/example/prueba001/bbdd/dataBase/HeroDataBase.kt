package com.example.prueba001.bbdd.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prueba001.bbdd.dao.HeroDao
import com.example.prueba001.bbdd.models.HeroDbModel

@Database(entities = [HeroDbModel::class], version = 1, exportSchema = false)
abstract class HeroDataBase : RoomDatabase() {

    abstract fun getHeroDao(): HeroDao

    companion object {

        @Volatile
        private var INSTANCE: HeroDataBase? = null

        private const val DB_NAME = "hero_database.db"

        fun getDatabase(context: Context): HeroDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
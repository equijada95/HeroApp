package com.example.prueba001.bbdd.dao

import androidx.room.*
import com.example.prueba001.bbdd.models.HeroDbModel


@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_list")
    suspend fun getAll(): List<HeroDbModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hero: HeroDbModel?)

    @Delete
    suspend fun delete(hero: HeroDbModel?)

}
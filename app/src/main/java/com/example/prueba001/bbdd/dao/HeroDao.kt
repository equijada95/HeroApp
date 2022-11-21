package com.example.prueba001.bbdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.prueba001.bbdd.models.HeroDbModel


@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_list")
    fun getAll(): LiveData<List<HeroDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hero: HeroDbModel?)

    @Delete
    suspend fun delete(hero: HeroDbModel?)

}
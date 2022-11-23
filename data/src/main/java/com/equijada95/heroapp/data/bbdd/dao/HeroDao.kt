package com.equijada95.heroapp.data.bbdd.dao

import androidx.room.*
import com.equijada95.heroapp.data.bbdd.models.HeroDbModel
import kotlinx.coroutines.flow.Flow


@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_list")
    fun getAll(): Flow<List<HeroDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hero: HeroDbModel?)

    @Delete
    suspend fun delete(hero: HeroDbModel?)

}
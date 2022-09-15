package com.example.prueba001.bbdd.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_list")
data class HeroDbModel(
    @PrimaryKey val id: Int
)

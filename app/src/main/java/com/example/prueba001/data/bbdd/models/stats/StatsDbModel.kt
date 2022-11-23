package com.example.prueba001.data.bbdd.models.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatsDbModel(
    @PrimaryKey val idStats: Int,
    @ColumnInfo val intelligence: Int,
    @ColumnInfo val strength: Int,
    @ColumnInfo val speed: Int,
    @ColumnInfo val durability: Int,
    @ColumnInfo val power: Int,
    @ColumnInfo val combat: Int
)

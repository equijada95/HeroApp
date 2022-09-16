package com.example.prueba001.bbdd.models.stats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prueba001.model.stats.StatsModel

@Entity
data class StatsDbModel(
    @PrimaryKey val id: Int,
    @ColumnInfo val intelligence: Int,
    @ColumnInfo val strength: Int,
    @ColumnInfo val speed: Int,
    @ColumnInfo val durability: Int,
    @ColumnInfo val power: Int,
    @ColumnInfo val combat: Int
) {

    companion object {
        @JvmStatic
        fun generateModel(model: StatsModel?) : StatsDbModel? {
            model?.let {
                return StatsDbModel(
                    Math.random().toInt(),
                    it.intelligence,
                    it.strength,
                    it.speed,
                    it.durability,
                    it.power,
                    it.combat
                )
            }
            return null
        }
    }

}

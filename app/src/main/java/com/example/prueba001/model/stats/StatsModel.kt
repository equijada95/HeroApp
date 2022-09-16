package com.example.prueba001.model.stats

import com.example.prueba001.bbdd.models.stats.StatsDbModel

data class StatsModel(
    var intelligence: Int,
    var strength: Int,
    var speed: Int,
    var durability: Int,
    var power: Int,
    var combat: Int
) {

    companion object {
        @JvmStatic
        fun generateModel(model: StatsDbModel?) : StatsModel? {
            model?.let {
                return StatsModel(
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

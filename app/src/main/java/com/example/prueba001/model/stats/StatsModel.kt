package com.example.prueba001.model.stats

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
        fun statsTest() = StatsModel(
            38,
            100,
            17,
            80,
            24,
            64
        )
    }
}

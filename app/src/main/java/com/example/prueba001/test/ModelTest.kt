package com.example.prueba001.test

import com.example.prueba001.model.HeroModel
import com.example.prueba001.model.appearance.AppearanceModel
import com.example.prueba001.model.biography.BiographyModel
import com.example.prueba001.model.images.ImagesModel
import com.example.prueba001.model.stats.StatsModel

class ModelTest {

    companion object {

        fun listHeroTest(): List<HeroModel> {
            val list = mutableListOf<HeroModel>()
            repeat(20) {
                list.add(heroTest())
            }
            return list
        }

        fun heroTest() = HeroModel(
            1,
            imagesTest(),
            "A-Bomb",
            statsTest(),
            appearanceTest(),
            biographyTest(),
            false
        )

        private fun imagesTest() = ImagesModel(
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/xs/1-a-bomb.jpg",
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/sm/1-a-bomb.jpg",
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/md/1-a-bomb.jpg",
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/1-a-bomb.jpg"
        )

        private fun statsTest() = StatsModel(
            38,
            100,
            17,
            80,
            24,
            64
        )

        private fun appearanceTest() = AppearanceModel(
            "Male",
            "Human",
            listOf("6,8", "203 cm"),
            listOf("980 lb", "441 kg"),
            "Yellow",
            "No hair"
        )

        private fun biographyTest() = BiographyModel(
            "Richard Milhouse Jones",
            "No alter egos found.",
            listOf("Rick Jones"),
            "Scarsdale, Arizona",
            "Hulk Vol 2 #2 (April, 2008) (as A-Bomb)",
            "Marvel Comics",
            "good"
        )
    }
}
package com.example.prueba001.model.appearance

data class AppearanceModel(
    var gender: String,
    var race: String,
    var height: List<String>,
    var weight: List<String>,
    var eyeColor: String,
    var hairColor: String
) {

    companion object {
        @JvmStatic
        fun appearanceTest() = AppearanceModel(
            "Male",
            "Human",
            listOf("6,8", "203 cm"),
            listOf("980 lb", "441 kg"),
            "Yellow",
            "No hair"
        )
    }
}


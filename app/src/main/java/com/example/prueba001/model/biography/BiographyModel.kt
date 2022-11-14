package com.example.prueba001.model.biography

data class BiographyModel(
    var fullName: String,
    var alterEgos: String,
    var aliases: List<String>,
    var placeOfBirth: String,
    var firstAppearance: String,
    var publisher: String,
    var alignment: String
) {

    companion object {
        @JvmStatic
        fun biographyTest() = BiographyModel(
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
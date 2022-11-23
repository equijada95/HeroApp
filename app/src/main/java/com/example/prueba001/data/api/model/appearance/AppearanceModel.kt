package com.example.prueba001.data.api.model.appearance

data class AppearanceModel(
    var gender: String,
    var race: String,
    var height: List<String>,
    var weight: List<String>,
    var eyeColor: String,
    var hairColor: String
)


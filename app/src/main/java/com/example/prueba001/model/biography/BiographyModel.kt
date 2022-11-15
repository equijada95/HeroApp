package com.example.prueba001.model.biography

data class BiographyModel(
    var fullName: String,
    var alterEgos: String,
    var aliases: List<String>,
    var placeOfBirth: String,
    var firstAppearance: String,
    var publisher: String,
    var alignment: String
)
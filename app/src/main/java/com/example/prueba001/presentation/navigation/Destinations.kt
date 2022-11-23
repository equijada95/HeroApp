package com.example.prueba001.presentation.navigation

sealed class Destinations(
    val route: String
) {
    object List: Destinations("List")
    object Detail: Destinations("Detail/{hero}") {
        fun createRoute(heroJson: String) = "Detail/$heroJson"
    }
}

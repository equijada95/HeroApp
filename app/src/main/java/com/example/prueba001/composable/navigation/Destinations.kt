package com.example.prueba001.composable.navigation

sealed class Destinations(
    val route: String
) {
    object List: Destinations("List")
    object Detail: Destinations("Detail/{hero}") {
        fun createRoute(heroJson: String) = "Detail/$heroJson"
    }
}

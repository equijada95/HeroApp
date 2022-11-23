package com.example.prueba001.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba001.presentation.detail.DetailComposable
import com.example.prueba001.presentation.list.ListComposable
import com.example.prueba001.data.api.model.HeroModel
import com.example.prueba001.domain.utils.decode
import com.example.prueba001.domain.utils.encode

@Composable
fun NavigationController(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Destinations.List.route) {
        composable(Destinations.List.route) { ListComposable { hero ->
            hero.encode()?.let { encoded ->
                navController.navigate(Destinations.Detail.createRoute(encoded))
            }
        } }
        composable(Destinations.Detail.route) { navBackEntry ->
            val encoded = navBackEntry.arguments?.getString("hero") ?: return@composable
            encoded.decode(HeroModel::class.java)?.let { hero ->
                DetailComposable(hero = hero)
            }
        }
    }
}
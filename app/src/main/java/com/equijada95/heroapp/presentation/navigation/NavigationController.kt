package com.equijada95.heroapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.equijada95.heroapp.presentation.detail.view.DetailComposable
import com.equijada95.heroapp.presentation.list.view.ListComposable
import com.equijada95.heroapp.domain.utils.decode
import com.equijada95.heroapp.domain.utils.encode

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
            encoded.decode(com.equijada95.heroapp.data.api.model.HeroModel::class.java)?.let { hero ->
                DetailComposable(hero = hero)
            }
        }
    }
}
package com.example.prueba001.composable.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba001.viewModels.DataBaseViewModel
import com.example.prueba001.composable.detail.DetailComposable
import com.example.prueba001.composable.list.ListComposable
import com.example.prueba001.model.HeroModel
import com.example.prueba001.utils.decode
import com.example.prueba001.utils.encode
import com.example.prueba001.viewModels.ListViewModel

@Composable
fun NavigationController(
    navController: NavHostController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel(),
    dbViewModel: DataBaseViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = Destinations.List.route) {
        composable(Destinations.List.route) { ListComposable(listViewModel) { hero ->
            hero.encode()?.let { encoded ->
                navController.navigate(Destinations.Detail.createRoute(encoded))
            }
        } }
        composable(Destinations.Detail.route) { navBackEntry ->
            val encoded = navBackEntry.arguments?.getString("hero") ?: return@composable
            encoded.decode(HeroModel::class.java)?.let { hero ->
                DetailComposable(hero = hero, dbViewModel = dbViewModel)
            }
        }
    }
}
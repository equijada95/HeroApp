package com.example.prueba001.composable.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel
import com.example.prueba001.composable.list.ListComposable
import com.example.prueba001.viewModels.HeroViewModel

@Composable
fun NavigationController(
    navController: NavHostController = rememberNavController(),
    heroViewModel: HeroViewModel = hiltViewModel(),
    dbViewModel: DataBaseViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = Destinations.List.route) {
        composable(Destinations.List.route) { ListComposable(heroViewModel, dbViewModel) }
        composable(Destinations.Detail.route) {}
    }
}
package com.example.prueba001.composable.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel
import com.example.prueba001.composable.detail.DetailComposable
import com.example.prueba001.composable.list.ListComposable
import com.example.prueba001.model.HeroModel
import com.example.prueba001.viewModels.HeroViewModel
import com.google.gson.Gson

@Composable
fun NavigationController(
    navController: NavHostController = rememberNavController(),
    heroViewModel: HeroViewModel = hiltViewModel(),
    dbViewModel: DataBaseViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = Destinations.List.route) {
        composable(Destinations.List.route) { ListComposable(heroViewModel, dbViewModel) {
            val heroJson = Gson().toJson(it)

            navController.navigate(Destinations.Detail.createRoute(heroJson)) // TODO CORREGIR ERROR DE PARSEO
        } }
        composable(Destinations.Detail.route) { navBackEntry ->
            val heroJson = navBackEntry.arguments?.getString("hero")
            val hero = Gson().fromJson(heroJson, HeroModel::class.java)
            DetailComposable(hero = hero)
        }
    }
}
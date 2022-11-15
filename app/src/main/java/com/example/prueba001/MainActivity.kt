package com.example.prueba001

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba001.composable.navigation.NavigationController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationController()
        }
    }

    private fun openList() {
    //    val transaction = supportFragmentManager.beginTransaction()
    //    transaction.add(binding.container.id, ListFragment())
    //    transaction.addToBackStack(null)
    //    transaction.commit()

    //    lateinit var favorites : List<HeroDbModel>
    //    val dataBaseViewModel = ViewModelProvider(this).get(DataBaseViewModel::class.java)
    //    dataBaseViewModel.getAllFavs()?.observe(this) { favs ->
    //        favorites = favs
    //    }
//
    //    val viewModel = ViewModelProvider(this).get(HeroViewModel::class.java)
    //    viewModel.getHeroes().observe(this) { heroModels ->
    //        heroModels.getHeroFromFavorites(favorites) {
    //            it.isFavorite = true
    //        }
    //    }
    }
}
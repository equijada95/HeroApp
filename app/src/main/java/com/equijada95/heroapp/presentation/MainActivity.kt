package com.equijada95.heroapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.equijada95.heroapp.presentation.navigation.NavigationController
import com.equijada95.heroapp.presentation.theme.HeroAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeroAppTheme {
                NavigationController()
            }
        }
    }
}
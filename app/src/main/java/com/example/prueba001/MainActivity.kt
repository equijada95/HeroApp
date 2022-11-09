package com.example.prueba001

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.prueba001.composable.Detail
import com.example.prueba001.databinding.ActivityMainBinding
import com.example.prueba001.fragments.list.ListFragment
import com.example.prueba001.model.HeroModel
import com.example.prueba001.ui.theme.HeroAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateView()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.container.id, ListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openDetail(hero: HeroModel) {
     //   actionBar = supportActionBar
     //   actionBar?.setDisplayHomeAsUpEnabled(true)
     //   val transaction = supportFragmentManager.beginTransaction()
     //   val fr = DetailFragment.newInstance(hero)
     //   transaction.add(binding.container.id, fr)
     //   transaction.addToBackStack(null)
     //   transaction.commit()
        setContent {
            HeroAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Detail(hero)
                }
            }
        }
    }

    private fun inflateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        actionBar?.setDisplayHomeAsUpEnabled(false)
        supportFragmentManager.popBackStack()
        return true
    }
}
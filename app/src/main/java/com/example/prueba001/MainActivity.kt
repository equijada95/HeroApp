package com.example.prueba001

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba001.composable.DetailFragment
import com.example.prueba001.databinding.ActivityMainBinding
import com.example.prueba001.fragments.list.ListFragment
import com.example.prueba001.model.HeroModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateView()
        openList()
    }

    fun openDetail(hero: HeroModel) {
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val transaction = supportFragmentManager.beginTransaction()
        val fr = DetailFragment.newInstance(hero)
        transaction.add(binding.container.id, fr)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun inflateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun openList() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.container.id, ListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        actionBar?.setDisplayHomeAsUpEnabled(false)
        supportFragmentManager.popBackStack()
        return true
    }
}
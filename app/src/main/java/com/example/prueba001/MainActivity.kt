package com.example.prueba001

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba001.databinding.ActivityMainBinding
import com.example.prueba001.fragments.detail.DetailFragment
import com.example.prueba001.fragments.list.ListFragment
import com.example.prueba001.model.HeroModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateView()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.container.id, ListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openDetail(hero: HeroModel) {
        val ft = supportFragmentManager.beginTransaction()
        val fr = DetailFragment.newInstance(hero)
        ft.add(binding.container.id, fr)
        ft.commit()
    }

    private fun inflateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}
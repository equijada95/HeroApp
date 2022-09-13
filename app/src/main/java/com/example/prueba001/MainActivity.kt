package com.example.prueba001

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba001.databinding.ActivityMainBinding
import com.example.prueba001.fragments.list.ListFragment
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

    private fun inflateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}
package com.example.challenge2_foodapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.databinding.ActivityMainBinding
import com.example.challenge2_foodapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val homeFragment = HomeFragment() // Ganti dengan fragment beranda yang sesuai
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, homeFragment)
            .commit()

    }
}
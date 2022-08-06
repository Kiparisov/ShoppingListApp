package com.kiparisov.shoppinglistapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kiparisov.shoppinglistapp.databinding.ActivityMainBinding
import com.kiparisov.shoppinglistapp.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.list.observe(this){
            Log.d("MainActivityTest", "onCreate: $it")
        }
    }
}
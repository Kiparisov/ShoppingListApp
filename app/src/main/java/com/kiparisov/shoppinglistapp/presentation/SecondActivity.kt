package com.kiparisov.shoppinglistapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.kiparisov.shoppinglistapp.R
import com.kiparisov.shoppinglistapp.databinding.ActivitySecondBinding
import com.kiparisov.shoppinglistapp.domain.ShopItem

class SecondActivity : AppCompatActivity() {
    val binding: ActivitySecondBinding by lazy{
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data = intent.extras
        val shopItem: ShopItem? = data?.get("item") as ShopItem?
        shopItem?.let { setTextInEditText(it) }

    }

    private fun setTextInEditText(shopItem: ShopItem){
        binding.name.setText(shopItem.name.substring(0, shopItem.name.length - 2))
        binding.count.setText(shopItem.count.toString())
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
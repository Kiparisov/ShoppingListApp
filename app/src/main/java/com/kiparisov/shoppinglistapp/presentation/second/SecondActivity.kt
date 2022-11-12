package com.kiparisov.shoppinglistapp.presentation.second

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.kiparisov.shoppinglistapp.R
import com.kiparisov.shoppinglistapp.databinding.ActivitySecondBinding
import com.kiparisov.shoppinglistapp.domain.ShopItem

class SecondActivity : AppCompatActivity() {
    private val viewModel: SecondViewModel by viewModels()
    private var screenMode: String = UNDEFINED_MODE
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    private val binding: ActivitySecondBinding by lazy{
        ActivitySecondBinding.inflate(layoutInflater)
    }

    private fun parseIntent(intent: Intent){
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent")
        }

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode!= MODE_EDIT) {
            throw RuntimeException("Param screen mode is incorrect: $mode")
        }
        screenMode = mode

        if (mode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    companion object{
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val UNDEFINED_MODE = "undefined_mode"

        fun newIntentAddItem(context: Context): Intent{
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Int): Intent{
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return intent
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        parseIntent(intent)
        if (savedInstanceState == null) {startFragmentInRightMode()}

        /*addTextWatchers()
        observeViewModel()*/
    }

    private fun startFragmentInRightMode(){
        val fragment: Fragment = when(screenMode){
            MODE_EDIT -> SecondFragment
                .newFragmentEditItem(shopItemId = shopItemId)
            MODE_ADD -> SecondFragment
                .newFragmentAddItem()
            else -> throw RuntimeException("Param screen mode is absent")
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }



    /*private fun observeViewModel(){
        viewModel.errorName.observe(this){
            val message = if (it){
                "Некорректное имя"
            }else{
                null
            }
            binding.titleName.error = message
        }

        viewModel.errorCount.observe(this){
            val message = if (it){
                "Некорректное имя"
            }else{
                null
            }
            binding.titleCount.error = message
        }

        viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }


    private fun addTextWatchers(){
        binding.name.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetNameError()
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }

        })

        binding.count.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetCountError()
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }

        })
    }

    private fun launchEditMode(){
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(this){
            setTextInEditText(it as ShopItem)
        }

        binding.buttonSave.setOnClickListener {
            editShopItem()
        }
    }

    private fun launchAddMode(){
        binding.buttonSave.setOnClickListener {
            addShopItem()
        }
    }
    private fun addShopItem(){
        val name = binding.name.text.toString()
        val count = binding.count.text.toString()

        viewModel.addShopItem(name, count)
    }

    private fun editShopItem(){
        val name = binding.name.text.toString()
        val count = binding.count.text.toString()

        viewModel.editShopItem(name, count)
    }

    private fun setTextInEditText(shopItem: ShopItem){
        binding.name.setText(shopItem.name)
        binding.count.setText(shopItem.count.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
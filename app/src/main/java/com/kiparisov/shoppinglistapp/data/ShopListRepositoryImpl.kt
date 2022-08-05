package com.kiparisov.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kiparisov.shoppinglistapp.domain.ShopItem
import com.kiparisov.shoppinglistapp.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private var autoGeneratedId: Int = 0

    init {
        addShopItem(
            ShopItem(
            name = "bread",
            count = 1,
            enabled = true
        ))

        addShopItem(ShopItem(
            name = "milk",
            count = 2,
            enabled = true
        ))

        addShopItem(ShopItem(
            name = "apple",
            count = 5,
            enabled = true
        ))
    }

    override fun addShopItem(shopItem: ShopItem) {
        shopItem.id = autoGeneratedId++
        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        val index = shopList.indexOf(oldElement)
        shopList[index] = shopItem
        updateList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id: $id not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList(){
        shopListLiveData.value = shopList.toList()
    }
}
package com.kiparisov.shoppinglistapp.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int): ShopItem

    fun getShopList(): List<ShopItem>

    fun deleteShopItem(shopItem: ShopItem)
}
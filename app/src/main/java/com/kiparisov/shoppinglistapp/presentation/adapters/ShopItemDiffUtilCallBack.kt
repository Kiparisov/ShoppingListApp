package com.kiparisov.shoppinglistapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.kiparisov.shoppinglistapp.domain.ShopItem

class ShopItemDiffUtilCallBack: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}
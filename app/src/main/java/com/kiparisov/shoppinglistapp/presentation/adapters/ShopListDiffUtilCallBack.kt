package com.kiparisov.shoppinglistapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.kiparisov.shoppinglistapp.domain.ShopItem

class ShopListDiffUtilCallBack(
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
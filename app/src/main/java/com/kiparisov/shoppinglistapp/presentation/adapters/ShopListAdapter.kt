package com.kiparisov.shoppinglistapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiparisov.shoppinglistapp.R
import com.kiparisov.shoppinglistapp.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ViewHolder>() {
    companion object{
        const val ENABLED_TYPE = 0
        const val DISABLED_TYPE = 1

        const val MAX_POOL_SIZE = 20
    }

    private var count = 0

    var onLongClickListener: ((ShopItem) -> Unit)? = null
    var onClickListener: ((ShopItem) -> Unit)? = null

    var shopList: List<ShopItem> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("onCreateViewHolder", "count:${++count} ")
        val layout = when(viewType){
            ENABLED_TYPE -> R.layout.item_shop_enabled
            DISABLED_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.name)
        val tvCount = view.findViewById<TextView>(R.id.count)

        fun bind(shopItem: ShopItem){
            view.setOnLongClickListener{
                onLongClickListener?.invoke(shopItem)
                true
            }
            view.setOnClickListener {
                onClickListener?.invoke(shopItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.bind(shopItem)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopList[position]
        return if (shopItem.enabled){
            ENABLED_TYPE
        }else{
            DISABLED_TYPE
        }
    }
}
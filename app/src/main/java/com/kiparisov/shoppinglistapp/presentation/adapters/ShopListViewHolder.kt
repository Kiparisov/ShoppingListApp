package com.kiparisov.shoppinglistapp.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiparisov.shoppinglistapp.R

class ShopListViewHolder(view: View): RecyclerView.ViewHolder(view){
    val tvName = view.findViewById<TextView>(R.id.name)
    val tvCount = view.findViewById<TextView>(R.id.count)
}
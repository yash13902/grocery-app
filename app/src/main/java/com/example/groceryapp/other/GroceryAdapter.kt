package com.example.groceryapp.other

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.groceryapp.data.db.entities.GroceryItems
import com.example.groceryapp.other.GroceryViewHolder.Companion.create


class GroceryAdapter(
    private val groceryItemClick: GroceryItemClick
): ListAdapter<GroceryItems, GroceryViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<GroceryItems>(){
        override fun areItemsTheSame(oldItem: GroceryItems, newItem: GroceryItems): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GroceryItems, newItem: GroceryItems): Boolean =
            oldItem==newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroceryViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.bind(getItem(position), groceryItemClick)
    }

}
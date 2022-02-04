package com.example.groceryapp.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.db.entities.GroceryItems
import com.example.groceryapp.databinding.GroceryItemBinding

class GroceryViewHolder(
    private val binding: GroceryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(items: GroceryItems, click: GroceryItemClick){
        with(binding){
            tvName.text = items.itemName
            tvQuantity.text = items.itemQuantity.toString()
            tvRate.text = items.itemPrice.toString()
            tvAmt.text = (items.itemPrice*items.itemQuantity).toString()

            idIVDelete.setOnClickListener{
                click.onItemClick(items, ClickAction.DELETE)
            }

        }
    }

    companion object {
        fun create(parent: ViewGroup): GroceryViewHolder {
            val binding = GroceryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return GroceryViewHolder(binding)
        }
    }
}
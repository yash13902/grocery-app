package com.example.groceryapp.other

import com.example.groceryapp.data.db.entities.GroceryItems

interface GroceryItemClick {
    fun onItemClick(groceryItems: GroceryItems, action: ClickAction)
}

enum class ClickAction{
    INSERT,
    DELETE
}
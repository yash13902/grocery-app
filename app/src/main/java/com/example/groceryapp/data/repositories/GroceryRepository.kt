package com.example.groceryapp.data.repositories

import android.content.Context
import com.example.groceryapp.data.db.GroceryDatabase
import com.example.groceryapp.data.db.entities.GroceryItems

class GroceryRepository(
    context: Context
) {
    private val databaseDao = GroceryDatabase.invoke(context).getGroceryDao()

    suspend fun insert(items: GroceryItems) = databaseDao.insert(items)
    suspend fun delete(items: GroceryItems) = databaseDao.delete(items)

    fun getAllItems() = databaseDao.getAllGroceryItems()


}
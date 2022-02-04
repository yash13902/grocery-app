package com.example.groceryapp.ui.groceryList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.data.db.entities.GroceryItems
import com.example.groceryapp.data.repositories.GroceryRepository
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application
): AndroidViewModel(application) {
    private val repository: GroceryRepository = GroceryRepository(application.applicationContext)

    fun insert(items: GroceryItems) = viewModelScope.launch {
        repository.insert(items)
    }

    fun delete(items: GroceryItems) = viewModelScope.launch {
        repository.delete(items)
    }

    fun getAllGroceryItems() = repository.getAllItems()

}

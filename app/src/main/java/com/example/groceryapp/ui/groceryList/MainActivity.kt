package com.example.groceryapp.ui.groceryList

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.other.ClickAction
import com.example.groceryapp.other.GroceryAdapter
import com.example.groceryapp.other.GroceryItemClick
import com.example.groceryapp.data.db.entities.GroceryItems
import com.example.groceryapp.databinding.ActivityMainBinding
import com.example.groceryapp.databinding.GroceryAddDialogBinding

class MainActivity : AppCompatActivity(), GroceryItemClick {
    private lateinit var viewModel: GroceryViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var groceryAdapter: GroceryAdapter
    private lateinit var dialogBinding: GroceryAddDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[GroceryViewModel::class.java]

        groceryAdapter = GroceryAdapter(this)

        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = groceryAdapter

            viewModel.getAllGroceryItems().observe(this@MainActivity, Observer {
                groceryAdapter.submitList(it)
            })

            floatingActionButton.setOnClickListener{
                openDialog()
            }

        }
    }

    private fun openDialog(){
        val dialog = Dialog(this)
        dialogBinding = GroceryAddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        with(dialogBinding){

            btnCancel.setOnClickListener{
                dialog.dismiss()
            }

            btnAdd.setOnClickListener{
                val itemName: String = etName.text.toString()
                val itemPrice = etPrice.text.toString()
                val itemQuantity : String = etQuantity.text.toString()

                if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){

                    val item = GroceryItems(itemName, itemQuantity.toInt(), itemPrice.toInt())
                    onItemClick(item, ClickAction.INSERT)
                    dialog.dismiss()
                } else{
                    Toast.makeText(applicationContext, "Please Enter all the data", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dialog.show()
    }

    override fun onItemClick(groceryItems: GroceryItems, action: ClickAction) {
        when(action){
            ClickAction.DELETE -> {
                viewModel.delete(groceryItems)
                Toast.makeText(applicationContext, "Item Deleted!!", Toast.LENGTH_SHORT).show()
            }
            ClickAction.INSERT ->{
                viewModel.insert(groceryItems)
                Toast.makeText(applicationContext, "Item Inserted!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
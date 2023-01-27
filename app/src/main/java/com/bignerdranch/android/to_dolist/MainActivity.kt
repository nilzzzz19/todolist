package com.bignerdranch.android.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var add:Button
    private lateinit var delete : Button
    private lateinit var clear:Button
    private lateinit var editText:EditText
    private lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing UI elements
        add = findViewById(R.id.add)
        delete = findViewById(R.id.delete)
        clear = findViewById(R.id.clear)
        editText = findViewById(R.id.editText)
        listView = findViewById(R.id.listView)

        // Initializing the array list to add the TO-DOs at a specific index.
        var itemlist = arrayListOf<String>()
        //Adapter will help us convert the generated array list into a view i.e, ListView
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {
            itemlist.add(editText.text.toString())
            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input
            // space or the eidt text space will be cleared
            editText.text.clear()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        clear.setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

    }
}
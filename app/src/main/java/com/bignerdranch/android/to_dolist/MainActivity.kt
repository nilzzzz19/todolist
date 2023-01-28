package com.bignerdranch.android.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bignerdranch.android.to_dolist.database.ToDoDatabase
import com.bignerdranch.android.to_dolist.model.ToDoItem
import com.bignerdranch.android.to_dolist.viewmodels.ToDoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var add:Button
    private lateinit var delete : Button
    private lateinit var clear:Button
    private lateinit var editText:EditText
    private lateinit var listView:ListView
    private lateinit var toDoViewModel: ToDoViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing UI elements
        add = findViewById(R.id.add)
        delete = findViewById(R.id.delete)
        clear = findViewById(R.id.clear)
        editText = findViewById(R.id.editText)
        listView = findViewById(R.id.listView)

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        //database startup
        val db = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java, "to_do_items_db"
        ).build()
        val dao = db.toDoItemDao()

        // Initializing the array list to add the TO-DOs at a specific index.
        var itemlist = arrayListOf<ToDoItem>()
        //Adapter will help us convert the generated array list into a view i.e, ListView
        var adapter = ArrayAdapter<ToDoItem>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {
            val toDo = ToDoItem(0, editText.text.toString())
            toDoViewModel.insert(toDo)
            editText.text.clear()
        }

        delete.setOnClickListener{
            val pos: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count-1
            while (item-- >=0){
                if(pos.get(item)){
                    toDoViewModel.delete(itemlist.get(item))
                }
            }
            pos.clear()
            adapter.notifyDataSetChanged()
        }

        clear.setOnClickListener {
            toDoViewModel.clear()
        }

        toDoViewModel.allToDo.observe(this, Observer {
            itemlist.clear()
            itemlist.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }
}
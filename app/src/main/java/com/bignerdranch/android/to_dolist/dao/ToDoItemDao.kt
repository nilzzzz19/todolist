package com.bignerdranch.android.to_dolist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.to_dolist.model.ToDoItem

@Dao
interface ToDoItemDao {

    @Insert
    fun insert(item:ToDoItem)

    @Query("Delete from to_do_items where id=:id")
    fun delete(id: Int)

    @Query("SELECT * FROM to_do_items")
    fun getAll(): LiveData<List<ToDoItem>>

    @Query("Delete from to_do_items")
    fun deleteAll()
}
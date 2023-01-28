package com.bignerdranch.android.to_dolist.repositories

import androidx.lifecycle.LiveData
import com.bignerdranch.android.to_dolist.dao.ToDoItemDao
import com.bignerdranch.android.to_dolist.model.ToDoItem

class ToDoRepository(private val toDoDao: ToDoItemDao) {
    val allToDos: LiveData<List<ToDoItem>> = toDoDao.getAll()

    suspend fun insert(toDoItem: ToDoItem) {
        toDoDao.insert(toDoItem)
    }

    suspend fun delete(id: Int) {
        toDoDao.delete(id)
    }
    suspend fun clear(toDoItem: List<ToDoItem>) {
        toDoDao.deleteAll()
    }
}

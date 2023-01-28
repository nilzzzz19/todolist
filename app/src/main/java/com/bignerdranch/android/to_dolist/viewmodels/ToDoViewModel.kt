package com.bignerdranch.android.to_dolist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.bignerdranch.android.to_dolist.database.ToDoDatabase
import com.bignerdranch.android.to_dolist.model.ToDoItem
import com.bignerdranch.android.to_dolist.repositories.ToDoRepository
import kotlinx.coroutines.Dispatchers

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoRepository
    val allToDo: LiveData<List<ToDoItem>>

    init {
        val toDoDao = ToDoDatabase.getDatabase(application, viewModelScope).toDoDao()
        repository = ToDoRepository(toDoDao)
        allToDo = repository.allToDo
    }

    fun insert(toDo: ToDoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(toDo)
    }

    fun delete(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    fun clear(toDo: List<ToDoItem>) = viewModelScope.launch(Dispatchers.IO) {
        repository.clear()
    }
}

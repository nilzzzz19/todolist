package com.bignerdranch.android.to_dolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*a data class is a class that is used to store data. It is similar to a regular class,
but it has some additional functionality that makes it more convenient to use for storing data.

A data class has the following characteristics:

It must be declared using the data keyword.
It must have at least one primary constructor with at least one parameter.
All of the primary constructor's parameters must be marked as val or var.
It automatically gets some useful functions such as toString, equals, hashCode, copy
and componentN functions, where N is the number of the constructor's parameters.*/

@Entity(tableName = "to_do_items")
data class ToDoItem( @PrimaryKey(autoGenerate = true) val id: Int, val task: String){

}


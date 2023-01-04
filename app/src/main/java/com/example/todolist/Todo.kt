package com.example.todolist

/**
 *  _Todo class
 *
 * @author Pimentel
 */
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)
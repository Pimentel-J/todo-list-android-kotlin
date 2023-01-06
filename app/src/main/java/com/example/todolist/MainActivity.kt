package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * _Todo List App (MainActivity)
 *
 * @author Pimentel
 */
class MainActivity : AppCompatActivity() {

    // Global variable (initialize later - line 22)
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        // LinearLayoutManager: arrange the items on a grid
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        /**
         * Reads the input and add it to the list
         */
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            // Basic empty validation
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        /**
         * Deletes all done todos
         */
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
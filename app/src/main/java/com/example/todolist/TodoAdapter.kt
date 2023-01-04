package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

/**
 * Contains the main logic (inherits RecyclerView)
 *
 * @author Pimentel
 */
class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    /**
     * View Holder  (inherits RecycleView)
     */
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * Creates a View Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            // Get a reference for a view
            // And inflate the layout (convert the .xml layout to the real view, that can be used in the code)
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    /**
     * Adds a _todo item
     */
    fun addTodo(todo: Todo) {
        todos.add(todo)
        // Notify to update RecyclerView
        notifyItemInserted(todos.size - 1)
    }

    /**
     * Deletes checked _todo items
     */
    fun deleteDoneTodos() {
        // Goes throw the _todo list and change the isChecked accordingly
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    /**
     * Strike through line toggle
     */
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    /**
     * Binds the data from the todos list to the views
     */
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        // itemView contains those views (note: with 'apply' there's no need to prepend holder to an item)
        holder.itemView.apply {
            tvTodoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, currentTodo.isChecked)
            // Note: '_' -> a parameter that's not needed (buttonView)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    /**
     * Returns the amount of items of the list
     */
    override fun getItemCount(): Int {
        return todos.size
    }
}
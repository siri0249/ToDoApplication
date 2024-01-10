package com.sirishajogu.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val description: String,
    val dueDate: String,
    val priority: String,
    var completed: Boolean
) {
    val status: String
        get() = if (completed) "Completed" else "In Progress"
}

fun NewTodo(task: String, description: String, dueDate: String, priority: String) =
    TodoModel(title = task, description = description, dueDate = dueDate, priority = priority, completed = false)

package com.sirishajogu.todo.ui

import android.util.Log
import androidx.lifecycle.*
import com.sirishajogu.todo.data.NewTodo
import com.sirishajogu.todo.data.TodoDao
import com.sirishajogu.todo.data.TodoModel
import kotlinx.coroutines.launch

class MainViewModel(private val todoDao: TodoDao) : ViewModel() {

    fun getTodos() = todoDao.getList().asLiveData()

    fun toggleTodoStatus(id: Int, updatedStatus: Boolean) {
        viewModelScope.launch {
            todoDao.toggleTodo(id, updatedStatus)
        }
    }
    fun deleteTask(todo: TodoModel) {
        viewModelScope.launch {
            todoDao.delete(todo)
        }
    }
    fun insertTask(task: String, description: String, dueDate: String, priority: String) {
        viewModelScope.launch {
            todoDao.insert(NewTodo(task, description, dueDate, priority))
        }
    }
    override fun onCleared() {
        Log.d("test", "viewmodel destroyed")
        super.onCleared()
    }

}
package com.sirishajogu.todo.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirishajogu.todo.data.TodoDao
import com.sirishajogu.todo.ui.MainViewModel

class MViewModelFactory(private val todoDao: TodoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainViewModel::class.java -> {
                MainViewModel(todoDao) as T
            }
            else -> {
                throw IllegalStateException()
            }
        }

    }
}
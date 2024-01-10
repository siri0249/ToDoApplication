package com.sirishajogu.todo.ui

import com.sirishajogu.todo.ui.create.CreateTodoActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sirishajogu.todo.R


import com.sirishajogu.todo.data.DbService
import com.sirishajogu.todo.data.TodoDao
import com.sirishajogu.todo.service.MViewModelFactory
import com.sirishajogu.todo.ui.adapter.TodoAdapter




class MainActivity : AppCompatActivity() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: TodoAdapter
  private lateinit var fab: FloatingActionButton
  private lateinit var viewModel: MainViewModel
  private val todoDao: TodoDao by lazy { DbService.todoDao(this.applicationContext) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel = ViewModelProvider(this, MViewModelFactory(todoDao)).get(MainViewModel::class.java)

    adapter = TodoAdapter(
      { id, updatedStatus -> viewModel.toggleTodoStatus(id, updatedStatus) },
      { position -> onDeleteClicked(position) } // Handle delete action
    )

    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.adapter = adapter

    fab = findViewById(R.id.floatingActionButton)
    fab.setOnClickListener {
      startActivity(Intent(this, CreateTodoActivity::class.java))
    }

    listenToDataUpdates()
  }

  private fun listenToDataUpdates() {
    viewModel.getTodos().observe(this) {
      adapter.list = it
    }
  }

  private fun onDeleteClicked(position: Int) {
    val todoToDelete = adapter.list.getOrNull(position)
    todoToDelete?.let {
      viewModel.deleteTask(it)
    }
  }

  override fun onResume() {
    super.onResume()
    if (!viewModel.getTodos().hasActiveObservers()) {
      listenToDataUpdates()
    }
  }
}


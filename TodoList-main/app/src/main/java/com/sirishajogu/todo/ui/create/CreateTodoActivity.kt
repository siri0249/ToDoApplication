package com.sirishajogu.todo.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.sirishajogu.todo.R


import com.sirishajogu.todo.data.DbService
import com.sirishajogu.todo.data.TodoDao
import com.sirishajogu.todo.service.MViewModelFactory
import com.sirishajogu.todo.ui.MainViewModel

class CreateTodoActivity : AppCompatActivity() {

  private lateinit var taskField: EditText
  private lateinit var descriptionField: EditText
  private lateinit var dueDateField: DatePicker
  private lateinit var prioritySpinner: Spinner
  private lateinit var saveButton: AppCompatButton

  private lateinit var viewModel: MainViewModel
  private val todoDao: TodoDao by lazy { DbService.todoDao(this.applicationContext) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_create_todo)

    supportActionBar?.title = "Add Todo"

    viewModel = ViewModelProvider(this, MViewModelFactory(todoDao))[MainViewModel::class.java]

    taskField = findViewById(R.id.newTodoTextField)
    descriptionField = findViewById(R.id.descriptionField)
    dueDateField = findViewById(R.id.dueDateField)
    prioritySpinner = findViewById(R.id.prioritySpinner)
    saveButton = findViewById(R.id.saveTodoButton)

    saveButton.setOnClickListener {
      val title = taskField.text.toString()
      val description = descriptionField.text.toString()
      val dueDate = "Due Date: ${dueDateField.dayOfMonth}/${dueDateField.month + 1}/${dueDateField.year}"
      val priority = "Priority: ${prioritySpinner.selectedItem}"

      viewModel.insertTask(title, description, dueDate, priority)
      finish()
    }
  }
}

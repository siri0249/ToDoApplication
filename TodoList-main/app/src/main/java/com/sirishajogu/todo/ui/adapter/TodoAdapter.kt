package com.sirishajogu.todo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.sirishajogu.todo.R
import com.sirishajogu.todo.data.TodoModel

class TodoAdapter(
    private val onClick: (itemId: Int, updatedStatus: Boolean) -> Unit,
    private val onDeleteClick: (position: Int) -> Unit
) : RecyclerView.Adapter<TodoViewHolder>() {

    var list: List<TodoModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_item, parent, false)
        return TodoViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = list[position]
        holder.todoTitle.text = item.title
        holder.todoDescription.text = item.description // Display description
        holder.todoDueDate.text = item.dueDate // Display due date
        holder.todoPriority.text = item.priority // Display priority
        holder.todoCheckBox.isChecked = item.completed

        holder.customButton.setOnClickListener {
            val currentStatus = holder.customButton.text.toString()
            val newStatus = if (currentStatus == "In Progress") {
                "Completed"
            } else {
                "In Progress"
            }
            holder.customButton.text = newStatus
            val newBackground = if (newStatus == "In Progress") {
                R.drawable.custombtn_bgorange // Set orange background
            } else {
                R.drawable.custombtn_bggreen // Set green background
            }
            holder.customButton.setBackgroundResource(newBackground)
        }

        holder.itemView.setOnClickListener {
            item.id?.let { id -> onClick(id, !item.completed) }
        }

        holder.deleteIcon.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount() = list.size
}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val todoTitle: TextView = itemView.findViewById(R.id.todoTitle)
    val todoDescription: TextView = itemView.findViewById(R.id.textDescription)
    val todoDueDate: TextView = itemView.findViewById(R.id.textDueDate)
    val todoPriority: TextView = itemView.findViewById(R.id.textPriority)
    val todoCheckBox: CheckBox = itemView.findViewById(R.id.todoCheckBox)
    val deleteIcon: ImageView = itemView.findViewById(R.id.deleteButton)
    val customButton: AppCompatButton = itemView.findViewById(R.id.customButton) // Assuming customButton is in the layout
}

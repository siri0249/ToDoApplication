package com.sirishajogu.todo.data

import android.content.Context
import androidx.room.Room

object DbService {
    private fun buildDb(context: Context): TodoDb {
        return Room.databaseBuilder(context, TodoDb::class.java, "todo_db")
            .build()
    }

    fun todoDao(context: Context) = buildDb(context).todoDao()
}
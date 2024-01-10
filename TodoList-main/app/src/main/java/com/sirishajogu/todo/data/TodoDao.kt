package com.sirishajogu.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: TodoModel)

    @Query("SELECT * from todos")
    fun getList(): Flow<List<TodoModel>>

    @Delete
    suspend fun delete(todo: TodoModel)

    @Query("UPDATE todos SET completed= :updatedStatus WHERE id=:id")
    suspend fun toggleTodo(id: Int, updatedStatus: Boolean)
}
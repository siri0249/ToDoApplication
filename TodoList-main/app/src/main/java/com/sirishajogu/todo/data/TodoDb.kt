package com.sirishajogu.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TodoModel::class], version = 2, exportSchema = false)
abstract class TodoDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Perform necessary migrations
                database.execSQL("ALTER TABLE todos ADD COLUMN description TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE todos ADD COLUMN priority TEXT NOT NULL DEFAULT 'LOW'")
                database.execSQL("ALTER TABLE todos ADD COLUMN dueDate TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}
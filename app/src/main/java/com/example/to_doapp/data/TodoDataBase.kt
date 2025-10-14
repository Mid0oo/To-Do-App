package com.example.to_doapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TodoEntity::class, TaskEntity::class],
    version = 1
)

abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
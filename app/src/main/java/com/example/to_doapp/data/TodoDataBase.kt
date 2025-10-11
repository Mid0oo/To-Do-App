package com.example.to_doapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TodoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
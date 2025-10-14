package com.example.to_doapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val todoId: Int,
    val title: String,
    val isDone: Boolean = false,
)

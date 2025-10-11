package com.example.to_doapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_doapp.ui.Labels

@Entity(tableName = "todo_table")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val task: String,
    val label: Labels,
    val isPinned: Boolean = false,
    val isDone: Boolean = false,
    val date: Long = System.currentTimeMillis()

)

package com.example.to_doapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_doapp.ui.Labels

@Entity(tableName = "todo_table")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val label: String = Labels.OTHER,
    val isPinned: Boolean = false,
    val date: Long = System.currentTimeMillis()
)

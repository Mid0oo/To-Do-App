package com.example.to_doapp.ui.state

import com.example.to_doapp.data.TodoWithTasks

data class ScreenState (
    val todoList: List<TodoWithTasks> = emptyList(),
    val pinnedList: List<TodoWithTasks> = emptyList(),
    val isPinned: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
    )



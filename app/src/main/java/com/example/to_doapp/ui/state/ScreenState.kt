package com.example.to_doapp.ui.state

import com.example.to_doapp.data.TodoEntity

data class ScreenState (
    val todoList: List<TodoEntity> = emptyList(),
    val pinnedList: List<TodoEntity> = emptyList(),
    val isPinned: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
    )



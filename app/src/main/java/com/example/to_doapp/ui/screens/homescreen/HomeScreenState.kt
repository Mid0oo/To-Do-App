package com.example.to_doapp.ui.screens.homescreen

import com.example.to_doapp.model.TodoUi

data class HomeScreenState(
    val todoList: List<TodoUi> = emptyList(),
    val pinnedList: List<TodoUi> = emptyList(),
    val isPinned: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

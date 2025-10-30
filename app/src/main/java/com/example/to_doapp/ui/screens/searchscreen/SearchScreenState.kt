package com.example.to_doapp.ui.screens.searchscreen

import com.example.to_doapp.model.TodoUi

data class SearchScreenState(
    val todoList: List<TodoUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

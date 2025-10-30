package com.example.to_doapp.ui.screens.taskscreen


import com.example.to_doapp.model.TaskUi
import com.example.to_doapp.model.TodoUi

data class TaskScreenState(
    val todo: TodoUi? = null,
    val tasks: List<TaskUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
    )

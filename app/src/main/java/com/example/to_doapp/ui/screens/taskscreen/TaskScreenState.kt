package com.example.to_doapp.ui.screens.taskscreen

import com.example.to_doapp.model.TaskUi

data class TaskScreenState(
    val tasks: List<TaskUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
    )

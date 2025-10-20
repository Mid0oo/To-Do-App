package com.example.to_doapp.model

import com.example.to_doapp.data.TodoWithTasks

data class TodoUi(
    val id: Int,
    val title: String,
    val label: String,
    val isPinned: Boolean,
    val tasks: List<TaskUi>
)
fun TodoWithTasks.toUi(): TodoUi  {
    return TodoUi(
        id = todo.id,
        title = todo.title,
        label = todo.label,
        isPinned = todo.isPinned,
        tasks = tasks.map {
            TaskUi(
                id = it.id,
                title = it.title,
                isDone = it.isDone
            )
        }
    )
}

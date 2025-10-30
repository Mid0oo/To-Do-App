package com.example.to_doapp.model

import com.example.to_doapp.data.TaskEntity
import com.example.to_doapp.data.TodoEntity
import com.example.to_doapp.data.TodoWithTasks

data class TodoUi(
    val id: Long,
    val title: String,
    val label: String,
    val isPinned: Boolean,
    val tasks: List<TaskUi>
)
data class TaskUi(
    val id: Long,
    val todoId: Long,
    val title: String,
    val isDone: Boolean,
)

fun TodoWithTasks.toUi(): TodoUi  {
    return TodoUi(
        id = todo.id,
        title = todo.title,
        label = todo.label,
        isPinned = todo.isPinned,
        tasks = tasks.map {
            it.toUi()
        }
    )
}
fun TaskEntity.toUi(): TaskUi {
    return TaskUi(
        id = id,
        todoId = todoId,
        title = title,
        isDone = isDone
    )
}
fun TaskUi.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        todoId = todoId,
        title = title,
        isDone = isDone
    )
}
fun TodoUi.toEntity(): TodoEntity {
    return  TodoEntity(
        id = id,
        title = title,
        label = label,
        isPinned = isPinned
    )
}






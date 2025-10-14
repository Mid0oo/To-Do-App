package com.example.to_doapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class TodoWithTasks(
    @Embedded val todo: TodoEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "todoId"
    )
    val tasks: List<TaskEntity>
)

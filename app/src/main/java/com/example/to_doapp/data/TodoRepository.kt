package com.example.to_doapp.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoWithTasks>>
    fun getPinnedTodos(): Flow<List<TodoWithTasks>>
    fun getTodoBySearch(query: String): Flow<List<TodoWithTasks>>
    suspend fun insertTodo(todo: TodoEntity)

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun insertTask(task: TaskEntity)

    suspend fun deleteTask(task: TaskEntity)
}

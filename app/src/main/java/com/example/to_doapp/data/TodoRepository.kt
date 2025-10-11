package com.example.to_doapp.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoEntity>>
    fun getPinnedTodos(): Flow<List<TodoEntity>>
    fun getTodoBySearch(query: String): Flow<List<TodoEntity>>
    suspend fun insertTodo(todo: TodoEntity)
    suspend fun deleteTodo(todo: TodoEntity)
}

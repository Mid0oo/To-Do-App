package com.example.to_doapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao

) : TodoRepository {
    override fun getAllTodos(): Flow<List<TodoEntity>> = dao.getAllTodos()

    override fun getPinnedTodos(): Flow<List<TodoEntity>> = dao.getPinnedTodos()

    override fun getTodoBySearch(query: String): Flow<List<TodoEntity>> = dao.getTodoBySearch(query)

    override suspend fun insertTodo(todo: TodoEntity) = dao.insertTodo(todo)

    override suspend fun deleteTodo(todo: TodoEntity) = dao.deleteTodo(todo)
}
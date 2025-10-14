package com.example.to_doapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao

) : TodoRepository {
    override fun getAllTodos(): Flow<List<TodoWithTasks>> = dao.getAllTodos()

    override fun getPinnedTodos(): Flow<List<TodoWithTasks>> = dao.getPinnedTodos()

    override fun getTodoBySearch(query: String): Flow<List<TodoWithTasks>> = dao.getTodoBySearch(query)

    override suspend fun insertTodo(todo: TodoEntity) = dao.insertTodo(todo)

    override suspend fun deleteTodo(todo: TodoEntity) = dao.deleteTodo(todo)

    override suspend fun insertTask(task: TaskEntity) = dao.insertTask(task)

    override suspend fun deleteTask(task: TaskEntity) = dao.deleteTask(task)


}
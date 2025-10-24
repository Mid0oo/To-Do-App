package com.example.to_doapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao

) : TodoRepository {
    override fun getAllTodos(): Flow<List<TodoWithTasks>> = dao.getAllTodos()

    override fun getPinnedTodos(): Flow<List<TodoWithTasks>> = dao.getPinnedTodos()

    override fun getTodoBySearch(query: String): Flow<List<TodoWithTasks>> = dao.getTodoBySearch(query)

    override fun getTodoById(id: Long): Flow<List<TodoWithTasks>> = dao.getTodoById(id)

    override suspend fun insertTodo(todo: TodoEntity) : Long = dao.insertTodo(todo)

    override suspend fun deleteTodo(todo: TodoEntity) = dao.deleteTodo(todo)

    override suspend fun insertTask(task: TaskEntity) : Long = dao.insertTask(task)

    override suspend fun deleteTask(task: TaskEntity) = dao.deleteTask(task)


}
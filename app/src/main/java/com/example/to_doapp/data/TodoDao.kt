package com.example.to_doapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo_table WHERE isPinned = 1 ")
    fun getPinnedTodos(): Flow<List<TodoEntity>>
    @Query("SELECT * FROM todo_table WHERE title LIKE :query")
    fun getTodoBySearch(query: String): Flow<List<TodoEntity>>

    @Upsert
    suspend fun insertTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

}
package com.example.to_doapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Transaction
    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): Flow<List<TodoWithTasks>>
    @Transaction
    @Query("SELECT * FROM todo_table WHERE isPinned = 1 ")
    fun getPinnedTodos(): Flow<List<TodoWithTasks>>
    @Transaction
    @Query("SELECT * FROM todo_table WHERE title LIKE :query")
    fun getTodoBySearch(query: String): Flow<List<TodoWithTasks>>

    @Transaction
    @Query("SELECT * FROM todo_table WHERE id = :id")
    fun getTodoById(id: Long): Flow<List<TodoWithTasks>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity) : Long

    @Upsert
    suspend fun insertTask(task: TaskEntity) : Long

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}
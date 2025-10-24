package com.example.to_doapp.di

import android.app.Application
import androidx.room.Room
import com.example.to_doapp.data.TodoDao
import com.example.to_doapp.data.TodoDatabase
import com.example.to_doapp.data.TodoRepository
import com.example.to_doapp.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase =
        Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()

    @Provides
    fun provideTodoDao(db: TodoDatabase) : TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository = TodoRepositoryImpl(dao)


}
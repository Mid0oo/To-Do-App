package com.example.to_doapp.ui.screens.taskscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.TaskEntity
import com.example.to_doapp.data.TodoRepository
import com.example.to_doapp.data.TodoWithTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TaskScreenState())
    val state: StateFlow<TaskScreenState> = _state.asStateFlow()

    private val _todo = MutableStateFlow<TodoWithTasks?>(null)
    val todo: StateFlow<TodoWithTasks?> = _todo.asStateFlow()

    fun loadTodo(id: Long) {
        viewModelScope.launch {
            repository.getTodoById(id).collect { todo ->
                _todo.value = todo.firstOrNull()
            }
        }
    }

    fun insertTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun pinTodo(todo: TodoWithTasks) {
        viewModelScope.launch {
            val updatedTodo = todo.copy(todo = todo.todo.copy(isPinned = !todo.todo.isPinned))
            repository.insertTodo(updatedTodo.todo)
        }
    }


}
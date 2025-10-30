package com.example.to_doapp.ui.screens.taskscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.TodoRepository
import com.example.to_doapp.model.TaskUi
import com.example.to_doapp.model.toEntity
import com.example.to_doapp.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TaskScreenState())
    val state: StateFlow<TaskScreenState> = _state.asStateFlow()


    fun loadTodo(id: Long) {
        viewModelScope.launch {
            repository.getTodoById(id).collect { todo ->
                val todo = todo.firstOrNull()?.toUi()
                _state.update { it.copy(todo = todo) }
            }
        }
    }

    fun addTask() {
        val todo = _state.value.todo ?: return
        val newTask = TaskUi(
            id = 0L,
            todoId = todo.id,
            title = "",
            isDone = false
        )
        viewModelScope.launch {
            repository.insertTask(newTask.toEntity())
        }
    }

    fun updateTask(task: TaskUi) {
        viewModelScope.launch {
            state.value.todo?.let { todo ->
                val updatedTasks = todo.tasks.map {
                    if (it.id == task.id) task else it
                }
                _state.update { it.copy(todo = todo.copy(tasks = updatedTasks)) }
            }
            repository.insertTask(task.toEntity())
        }
    }

    fun pinTodo() {
        val todo = state.value.todo ?: return
        viewModelScope.launch {
            val updatedTodo = todo.copy(isPinned = !todo.isPinned)
            repository.insertTodo(updatedTodo.toEntity())
            _state.update { it.copy(todo = updatedTodo) }
        }
    }

    fun checkTask(task: TaskUi) {
        viewModelScope.launch {
            val updatedTask = task.copy(isDone = !task.isDone)
            viewModelScope.launch {
                repository.insertTask(updatedTask.toEntity())
            }
        }
    }

    fun onTitleChange(title: String) {
        val todo = _state.value.todo ?: return
        viewModelScope.launch {
            repository.insertTodo(todo.copy(title = title).toEntity())
        }
        _state.update { it.copy(todo = todo.copy(title = title)) }
    }

    fun onLabelChange(label: String) {
        _state.update { it.copy(todo = it.todo?.copy(label = label)) }
        val updatedTodo = _state.value.todo?.copy(label = label) ?: return
        viewModelScope.launch {
            repository.insertTodo(updatedTodo.toEntity())
        }
    }

}



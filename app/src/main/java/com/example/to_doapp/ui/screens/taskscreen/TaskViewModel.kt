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
import kotlinx.coroutines.flow.firstOrNull
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
            try {
                repository.getTodoById(id).collect { todo ->
                    val todo = todo.firstOrNull()?.toUi()
                    _state.update { it.copy(todo = todo) }
                }

            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
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
            try {
                repository.insertTask(newTask.toEntity())
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun updateTask(task: TaskUi) {
        viewModelScope.launch {
            try {
                state.value.todo?.let { todo ->
                    val updatedTasks = todo.tasks.map {
                        if (it.id == task.id) task else it
                    }
                    _state.update { it.copy(todo = todo.copy(tasks = updatedTasks)) }
                }
                repository.insertTask(task.toEntity())

            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun pinTodo() {
        val todo = state.value.todo ?: return
        viewModelScope.launch {
            try {
                val updatedTodo = todo.copy(isPinned = !todo.isPinned)
                repository.insertTodo(updatedTodo.toEntity())
                _state.update { it.copy(todo = updatedTodo) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun checkTask(task: TaskUi) {
        viewModelScope.launch {
            try {
                val updatedTask = task.copy(isDone = !task.isDone)
                viewModelScope.launch {
                    repository.insertTask(updatedTask.toEntity())
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun onTitleChange(title: String) {
        val todo = _state.value.todo ?: return
        viewModelScope.launch {
            try {
                repository.insertTodo(todo.copy(title = title).toEntity())
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
        _state.update { it.copy(todo = todo.copy(title = title)) }
    }

    fun onLabelChange(label: String) {
        _state.update { it.copy(todo = it.todo?.copy(label = label)) }
        val updatedTodo = _state.value.todo?.copy(label = label) ?: return
        viewModelScope.launch {
            try {
                repository.insertTodo(updatedTodo.toEntity())
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun deleteIfEmpty(todoId: Long, title: String, tasksCount: Int) {
        viewModelScope.launch {
            if (title == "Title" && tasksCount == 0) {
                val todoWithTasksList = repository.getTodoById(todoId).firstOrNull()
                val todoUi = todoWithTasksList?.firstOrNull()?.toUi()
                todoUi?.let {
                    repository.deleteTodo(it.toEntity())
                }
            }
        }
    }
    fun deleteTask(task: TaskUi) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task.toEntity())
                loadTodo(task.todoId)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

}



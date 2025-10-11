package com.example.to_doapp.ui.screens.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.TodoEntity
import com.example.to_doapp.data.TodoRepository
import com.example.to_doapp.ui.TodoTabs
import com.example.to_doapp.ui.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TodoRepository

): ViewModel() {
    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    private val _selectedTab = MutableStateFlow(TodoTabs.All)
    val selectedTab: StateFlow<TodoTabs> = _selectedTab.asStateFlow()



    init {
        loadAllTodos()
        loadPinnedTodos()
    }
    private fun loadAllTodos() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            try {
                repository.getAllTodos().collect { todos ->
                    _state.value = state.value.copy(
                        todoList = todos,
                        isLoading = false
                    )

                }

            } catch (e: Exception) {
                _state.value = state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
    private fun loadPinnedTodos() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            try {
                repository.getPinnedTodos().collect { pinned ->
                    _state.value = state.value.copy(
                        pinnedList = pinned,
                        isLoading = false
                    )

                }

            } catch (e: Exception) {
                _state.value = state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }


    fun onSelectedTab(tab: TodoTabs){
        _selectedTab.value = tab
    }
    fun insertTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }

}


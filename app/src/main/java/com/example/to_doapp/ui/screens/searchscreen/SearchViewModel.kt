package com.example.to_doapp.ui.screens.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.TodoRepository
import com.example.to_doapp.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {


    private val _state = MutableStateFlow(SearchScreenState())
    val state: StateFlow<SearchScreenState> = _state.asStateFlow()

    fun onSearch(query: String) {
        _state.value = _state.value.copy(
            query = query,
            isLoading = true
        )
        viewModelScope.launch {
            if (query.isBlank()) {
                _state.value = _state.value.copy(
                    todoList = emptyList(),
                    isLoading = false
                )
                return@launch
            }
            try {
                repository.getTodoBySearch("%$query%").collect { todos ->
                    _state.value = _state.value.copy(
                        todoList = todos.map { it.toUi() },
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}



package com.example.to_doapp.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mytrainning.R
import com.example.to_doapp.data.TodoEntity
import com.example.to_doapp.model.TodoUi
import com.example.to_doapp.ui.TodoTabs
import com.example.to_doapp.ui.components.EmptyState
import com.example.to_doapp.ui.components.ListAndPinned
import com.example.to_doapp.ui.components.ListCard
import com.example.to_doapp.ui.components.LogoAndSearch
import com.example.to_doapp.ui.components.NewListButton
import com.example.to_doapp.ui.components.TodoFloatingButton
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val state = viewModel.state.collectAsState()
    val selectedTab = viewModel.selectedTab.collectAsState()
    Scaffold(
        floatingActionButton = {
            TodoFloatingButton(
                onClick = {
                    viewModel.viewModelScope.launch {
                        val newTodo = TodoEntity(title = "Title", isPinned = false)
                        val id = viewModel.insertTodo(newTodo)
                        navController.navigate("task/$id")
                    }
                }
            )
        }
    ) { paddingValues ->
        HomeScreenContent(
            allTodos = state.value.todoList,
            pinnedTodos = state.value.pinnedList,
            onSelectedTab = { viewModel.onSelectedTab(it) },
            selectedTab = selectedTab.value,
            onCardClick = { navController.navigate("task/$it") },
            onSearch = { navController.navigate("search") },
            onInsertButtonClick = {viewModel.viewModelScope.launch {
                val newTodo = TodoEntity(title = "Title", isPinned = false)
                val id = viewModel.insertTodo(newTodo)
                navController.navigate("task/$id")
            }},
            modifier = Modifier.padding(paddingValues)
        )
    }




}
@Composable
fun HomeScreenContent(
    allTodos: List<TodoUi>,
    pinnedTodos: List<TodoUi>,
    onSelectedTab: (TodoTabs) -> Unit,
    selectedTab: TodoTabs,
    onCardClick: (Long) -> Unit = {},
    onInsertButtonClick: () -> Unit = {},
    onSearch: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            LogoAndSearch(
                onClick = { onSearch() },
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .padding(horizontal = 24.dp)
            )
            ListAndPinned(
                onSelectedTab = { onSelectedTab(it) },
                selectedTab = selectedTab,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 24.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (selectedTab) {
                    TodoTabs.All -> {
                        if (allTodos.isEmpty()) {
                            item {
                                EmptyState(
                                    imageRes = R.drawable.all_empty,
                                    message = stringResource(R.string.oops_you_don_t_have_any_list),
                                    modifier = Modifier
                                        .padding(top = 80.dp)

                                )
                                NewListButton(
                                    onClick = {onInsertButtonClick()},
                                )
                            }
                        } else {
                            items(allTodos) {
                                ListCard(
                                    title = it.title,
                                    label = it.label,
                                    onClick = {onCardClick(it.id)},
                                    modifier = Modifier
                                        .padding(horizontal = 24.dp)
                                        .padding(bottom = 18.dp)

                                )

                            }

                        }
                    }
                    TodoTabs.Pinned -> {
                        if (pinnedTodos.isEmpty()) {
                            item {
                                EmptyState(
                                    imageRes = R.drawable.pinned_empty,
                                    message = "Oops! No pinned list yet...",
                                    modifier = Modifier
                                        .padding(horizontal = 50.dp)
                                )
                                NewListButton(
                                    onClick = {onInsertButtonClick()},
                                    modifier = Modifier
                                )

                            }
                        } else {
                            items(pinnedTodos) {
                                ListCard(
                                    title = it.title,
                                    label = it.label,
                                    onClick = {onCardClick(it.id)},
                                    modifier = Modifier
                                        .padding(horizontal = 24.dp)
                                        .padding(bottom = 18.dp)

                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreenContent(
        allTodos = emptyList(),
        pinnedTodos = emptyList(),
        onSelectedTab = {},
        selectedTab = TodoTabs.All
    )
}
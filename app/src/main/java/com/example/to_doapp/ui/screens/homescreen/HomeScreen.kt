package com.example.to_doapp.ui.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytrainning.R
import com.example.to_doapp.data.TodoEntity
import com.example.to_doapp.data.TodoWithTasks
import com.example.to_doapp.ui.TodoTabs
import com.example.to_doapp.ui.components.EmptyState
import com.example.to_doapp.ui.components.ListAndPinned
import com.example.to_doapp.ui.components.ListCard
import com.example.to_doapp.ui.components.LogoAndSearch
import com.example.to_doapp.ui.components.NewListButton

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
){
    val state = viewModel.state.collectAsState()
    val selectedTab = viewModel.selectedTab.collectAsState()


    HomeScreenContent(
        allTodos = state.value.todoList,
        pinnedTodos = state.value.pinnedList,
        onSelectedTab = {viewModel.onSelectedTab(it)},
        selectedTab = selectedTab.value
    )

}
@Composable
fun HomeScreenContent(
    allTodos: List<TodoWithTasks>,
    pinnedTodos: List<TodoWithTasks>,
    onSelectedTab: (TodoTabs) -> Unit,
    selectedTab: TodoTabs,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding(),
        contentPadding = PaddingValues(
            vertical = 24.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            LogoAndSearch(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .padding(horizontal = 24.dp)
            )
        }
        item {
            ListAndPinned(
                onSelectedTab = { onSelectedTab(it) },
                selectedTab = selectedTab,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            )
        }
        when (selectedTab) {
            TodoTabs.All -> {
                if (allTodos.isEmpty()) {
                    item {
                        EmptyState(
                            imageRes = R.drawable.all_empty,
                            message = stringResource(R.string.oops_you_don_t_have_any_list),
                            modifier = Modifier
                                .padding(top = 100.dp)

                        )
                        NewListButton(
                            onClick = {},
                            modifier = Modifier
                                //.padding(top = 26.dp)

                        )
                    }
                } else {
                    items(allTodos) {
                        ListCard(
                            title = it.todo.title,
                            label = it.todo.label,
                            onClick = {it.todo.id}
                        )

                    }

                }
            }
            TodoTabs.Pinned -> {
                if (pinnedTodos.isEmpty()) {
                    item {
                        EmptyState(
                            imageRes = R.drawable.pinned_empty,
                            message = "Oops! You don't have any pinned list",
                        )
                        NewListButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(top = 24.dp)
                        )

                    }
                } else {
                    items(pinnedTodos) {
                        ListCard(
                            title = it.todo.title,
                            label = it.todo.label,
                            onClick = {it.todo.id}
                        )
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
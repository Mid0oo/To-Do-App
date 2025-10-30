package com.example.to_doapp.ui.screens.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.to_doapp.model.TodoUi
import com.example.to_doapp.ui.components.ListCard
import com.example.to_doapp.ui.components.SearchBox
import com.example.to_doapp.ui.theme.Graphik



@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
    ){
    val state by viewModel.state.collectAsState()
    Scaffold { paddingValues ->
        SearchScreenContent(
            query = state.query,
            onQueryChange = { viewModel.onSearch(it) },
            onCancel = {navController.popBackStack()},
            todoList = state.todoList,
            onCardClick = {navController.navigate("task/$it")},
            modifier = Modifier.padding(paddingValues)
        )
    }

}
@Composable
fun SearchScreenContent(
    query: String,
    onQueryChange: (String) -> Unit,
    onCancel: () -> Unit,
    onCardClick: (Long) -> Unit = {},
    todoList: List<TodoUi> = emptyList(),
    modifier: Modifier = Modifier
    ){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBox(
                query = query,
                onQueryChange = {onQueryChange(it)},
                modifier = Modifier
                    .weight(0.8f)
            )
            Text(
                text = "Cancel",
                fontFamily = Graphik,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable{onCancel()}
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 24.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            ){
            items(todoList){
                ListCard(
                    title = it.title,
                    label = it.label,
                    onClick = {onCardClick(it.id)},
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                )
            }

        }

    }
}
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(){
    SearchScreenContent(
        query = "Search Your List",
        onQueryChange = {},
        onCancel = {}
    )
}

package com.example.to_doapp.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_doapp.ui.TodoTabs
import com.example.to_doapp.ui.components.ListAndPinned
import com.example.to_doapp.ui.components.LogoAndSearch
import com.example.to_doapp.ui.components.NewListButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 24.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item {
            LogoAndSearch(
                modifier = Modifier
                    .padding(bottom = 40.dp)
            )
        }
        item {
            ListAndPinned(
                onSelectedTab = {},
                selectedTab = TodoTabs.All
            )
        }

        item {
            NewListButton(
                onClick = {},
                modifier = Modifier
                    .padding(top = 24.dp)

            )
        }
    }
}
@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}
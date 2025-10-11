package com.example.to_doapp.ui.screens.searchscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_doapp.ui.components.SearchBox
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun SearchScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
    ){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBox(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier
                    .weight(0.8f)
            )
            Text(
                text = "Cancel",
                fontFamily = Graphik,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable{onCancel()}
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(){
    SearchScreen(
        query = "Search Your List",
        onQueryChange = {},
        onCancel = {}
    )
}

package com.example.to_doapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytrainning.R
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun SearchBox(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
    ){
    Row(
        modifier = modifier
            .height(43.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFE3E2E2),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color(0xFFF4F4F4))
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.Black,

        )
        BasicTextField(
            value = query,
            onValueChange = {onQueryChange(it)},
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = Graphik,
                fontWeight = FontWeight.Normal,
            ),
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}
@Preview
@Composable
fun SearchBoxPreview(){
    SearchBox(
        query = "Search Your List",
        onQueryChange = {}
    )
}

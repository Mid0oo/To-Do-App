package com.example.to_doapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun EmptyState(
    imageRes: Int,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentScale = ContentScale.Crop,
            contentDescription = "Empty List",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 90.dp)
        )
        Text(
            text = message,
            fontFamily = Graphik,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}
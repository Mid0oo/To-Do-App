package com.example.to_doapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytrainning.R
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun NewListButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .height(47.dp)
            .width(125.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.Black)
            .clickable{onClick},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "New List",
            tint = Color.White,
            modifier = Modifier
                .padding(end = 8.dp)

        )
        Text(
            text = "New List",
            color = Color.White,
            fontFamily = Graphik,
            fontWeight = FontWeight.Medium,
        )
    }
}
@Preview()
@Composable
fun NewListButtonPreview(){
    NewListButton(onClick = {})
}
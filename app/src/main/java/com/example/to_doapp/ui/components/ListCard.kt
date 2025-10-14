package com.example.to_doapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_doapp.ui.Labels
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun ListCard(
    title: String,
    label: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val labelColor = Labels.getColor(label)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(labelColor)
            .border(shape = RoundedCornerShape(16.dp), color = Color.Black, width = 2.dp)
            .clickable { onClick() }
            .padding(horizontal = 22.dp)
            .padding(vertical = 17.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontFamily = Graphik,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
        Box(
            modifier = Modifier
                .padding(top = 14.dp)
                .height(16.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = Color.Black),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = label,
                color = Color.White,
                fontFamily = Graphik,
                fontWeight = FontWeight.Medium,
                fontSize = 7.sp,
                modifier = Modifier
                    .padding(horizontal = 7.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ListCardPreview(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .background(Color.White)

    ){
        ListCard(
            title = "Work List",
            label = Labels.WORK,
        )
    }

}
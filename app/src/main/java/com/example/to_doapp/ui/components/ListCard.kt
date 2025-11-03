package com.example.to_doapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val labelColor = Labels.getColor(label)

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = Color.White,
            titleContentColor = Color.Black,
            textContentColor = Color.Black,
            title = { Text(text = "Delete List",
                fontFamily = Graphik ,
                fontWeight = FontWeight.Medium
            ) },
            text = { Text(
                text = "Are you sure you want to delete this list?",
                fontFamily = Graphik ,
                fontWeight = FontWeight.Medium
            ) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDialog = false
                    }
                ) {
                    Text (
                        "Delete",
                        color = Color.Red ,
                        fontFamily = Graphik ,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ){
                    Text (
                        "Cancel",
                        fontFamily = Graphik ,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        )
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(labelColor)
            .border(shape = RoundedCornerShape(16.dp), color = Color.Black, width = 1.dp)
            .clickable(onClick = onClick)
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { showDialog = true }
            )
            .padding(horizontal = 22.dp)
            .padding(vertical = 17.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontFamily = Graphik,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 14.dp)
        )
        Box(
            modifier = Modifier
                .height(20.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                color = Color.White,
                fontFamily = Graphik,
                fontWeight = FontWeight.Medium,
                fontSize = 7.sp,
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
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
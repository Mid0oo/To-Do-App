package com.example.to_doapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun ListAndPinned(
    selectedTap: String = "Pinned",
    onSelectedTap: (String) -> Unit,
    modifier: Modifier = Modifier,

){

    Row(
        modifier = modifier
            .height(47.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically,

    ){
        val tabs = listOf("All List", "Pinned")

        tabs.forEach { tab ->
            Box(
                modifier = Modifier
                    .height(47.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        if (selectedTap == tab)
                            Color(0xFF000000)
                        else Color.Transparent
                    )
                    .clickable { onSelectedTap(tab) }
                    .weight(1f),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = tab,
                    color = if (selectedTap == tab) Color.White else Color.Black,
                    fontFamily = Graphik,
                    fontWeight = FontWeight.Medium,
                    )

            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ListAndPinnedPreview(){
    ListAndPinned(
        onSelectedTap = {},
        modifier = Modifier
            .width(327.dp)

    )
}

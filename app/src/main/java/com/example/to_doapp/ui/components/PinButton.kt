package com.example.to_doapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import com.example.mytrainning.R
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun PinButton(
    onClick: (Boolean) -> Unit,
    isPinned: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isPinned) Color.Black else Color.White
    Row(
        modifier = modifier
            .height(24.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = backgroundColor)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 8.dp)
            .clickable { onClick(!isPinned)},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Icon(
            painter = painterResource(
                id = if (isPinned) R.drawable.ic_pin_selected else R.drawable.ic_pin
            ),
            contentDescription = "Pin",
            tint = if (isPinned) Color.White else Color.Black,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(
            text = if (isPinned) "Pinned" else "Pin",
            color = if (isPinned) Color.White else Color.Black,
            fontFamily = Graphik,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp
        )

    }
}
@Preview
@Composable
fun PinButtonPreview(){
    PinButton(onClick = {}, isPinned = true)
}
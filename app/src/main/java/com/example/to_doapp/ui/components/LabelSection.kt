package com.example.to_doapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun LabelSection(
    selectedLabel: String,
    onSelectedLabel: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val labels = listOf(
        Labels.PERSONAL,
        Labels.WORK,
        Labels.FINANCE,
        Labels.OTHER
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color(0xFFDADADA))
        )
        Text(
            text = "Choose a Label",
            color = Color.Black,
            fontFamily = Graphik,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 30.dp)

        )
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.forEach { label ->
                val isSelected = label == selectedLabel
                val backgroundColor by animateColorAsState(
                    targetValue = if (isSelected) Color.Black else Color(0xFFBFBFBF),
                    label = "bgAnim"
                )
                Box(
                    modifier = Modifier
                        .height(28.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = backgroundColor)
                        .clickable { onSelectedLabel(label) },
                    contentAlignment = Alignment.Center,

                    ) {
                    Text(
                        text = label,
                        color = Color.White,
                        fontFamily = Graphik,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)

                    )
                }

            }
        }

    }

}
@Preview(showBackground = true)
@Composable
fun LabelSectionPreview(){
    LabelSection(
        selectedLabel = Labels.WORK,
        onSelectedLabel = {}
    )
}
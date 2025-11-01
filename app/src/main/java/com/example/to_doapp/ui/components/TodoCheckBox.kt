package com.example.to_doapp.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.key.Key.Companion.Backspace
import androidx.compose.ui.input.key.Key.Companion.Enter
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun TodoCheckbox(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    onDeleted: () -> Unit,
    onEnter: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentText by remember { mutableStateOf(text) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .size(15.dp)
                .background(
                    color = if (checked) Color.Black else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable { onCheckedChange(!checked) },
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Canvas(modifier = Modifier.size(8.dp)) {
                    val yOffset = -size.height * 0.05f
                    drawLine(
                        color = Color.White,
                        start = Offset(x = size.width * 0.15f, y = size.height * 0.55f + yOffset),
                        end = Offset(x = size.width * 0.4f, y = size.height * 0.75f + yOffset),
                        strokeWidth = 5f,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(x = size.width * 0.4f, y = size.height * 0.75f + yOffset),
                        end = Offset(x = size.width * 0.85f, y = size.height * 0.25f + yOffset),
                        strokeWidth = 5f,
                        cap = StrokeCap.Round
                    )
                }
            }
        }
        BasicTextField(
            value = currentText,
            onValueChange = {
                currentText = it
                onValueChange(it) },
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = Graphik,
                fontWeight = FontWeight.Normal,
                textDecoration = if(checked) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Backspace -> {
                                if (currentText.isBlank()) {
                                    onDeleted()
                                    true
                                } else false
                            }
                            Enter -> {
                                onEnter()
                                true
                            }
                            else -> false
                        }
                    } else false
                }
        )
    }
}

@Preview
@Composable
fun TodoCheckBoxPreview(){

    TodoCheckbox(
        checked = true,
        onCheckedChange = {},
        onValueChange = {},
        text = "To-Do",
        onDeleted = {},
        onEnter = {}
    )
}
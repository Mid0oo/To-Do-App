package com.example.to_doapp.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
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
import com.example.to_doapp.ui.TodoTabs
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun ListAndPinned(
    selectedTab: TodoTabs,
    onSelectedTab: (TodoTabs) -> Unit,
    modifier: Modifier = Modifier,

){
    val tabs = listOf(TodoTabs.All, TodoTabs.Pinned)
    val transition = updateTransition(selectedTab, label = "Tab Transition")
    val indicatorOffset by transition.animateDp(label = "Indicator Offset") { tab ->
        if (tab == TodoTabs.All) 0.dp else 160.dp
    }


    Row(
        modifier = modifier
            .height(47.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically,

    ){

        tabs.forEach { tab ->
            Box(
                modifier = Modifier
                    .height(47.dp)
                    .width(184.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .offset(x = indicatorOffset)
                    .background(
                        if (tab == selectedTab)
                            Color(0xFF000000)
                        else Color.Transparent
                    )
                    .clickable { onSelectedTab(tab) }
                    .weight(1f),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = if (tab == TodoTabs.All) "All List" else "Pinned",
                    color = if (selectedTab == tab) Color.White else Color.Black,
                    fontFamily = Graphik,
                    fontWeight = FontWeight.Medium,
                    )

            }
        }


    }
}

@Preview(showBackground = false)
@Composable
fun ListAndPinnedPreview(){
    ListAndPinned(
        onSelectedTab = {},
        selectedTab = TodoTabs.All,
        modifier = Modifier
            .width(327.dp)

    )
}

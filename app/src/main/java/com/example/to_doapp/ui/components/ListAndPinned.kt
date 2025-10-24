package com.example.to_doapp.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
) {
    val tabs = listOf(TodoTabs.All, TodoTabs.Pinned)

    BoxWithConstraints(
        modifier = modifier
            .height(47.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFE5E5E5))
    ) {
        val tabWidth = maxWidth / tabs.size

        val transition = updateTransition(targetState = selectedTab, label = "tabTransition")

        val indicatorOffset by transition.animateDp(
            label = "indicatorOffset",
            transitionSpec = { tween(durationMillis = 250) }
        ) { tab ->
            when (tab) {
                TodoTabs.All -> 0.dp
                TodoTabs.Pinned -> tabWidth
            }
        }

        // Moving black indicator
        Box(
            modifier = Modifier
                .height(47.dp)
                .width(tabWidth)
                .offset(x = indicatorOffset)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
        )

        // Tabs content
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(47.dp)
                        .clickable { onSelectedTab(tab) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (tab == TodoTabs.All) "All List" else "Pinned",
                        color = if (selectedTab == tab) Color.White else Color.Gray,
                        fontFamily = Graphik,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListAndPinnedPreview() {
    ListAndPinned(
        selectedTab = TodoTabs.All,
        onSelectedTab = {},
        modifier = Modifier.fillMaxWidth()
    )
}


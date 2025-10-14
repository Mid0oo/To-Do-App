package com.example.to_doapp.ui

import androidx.compose.ui.graphics.Color


object Labels {
    const val PERSONAL = "Personal"
    const val WORK = "Work"
    const val FINANCE = "Finance"
    const val OTHER = "Other"


    fun getColor(label: String): Color {
        return when (label) {
            PERSONAL ->  Color(0xFFEDBBB4)
            WORK -> Color(0xFFFFF6E7)
            FINANCE -> Color(0xFFF3E4F6)
            OTHER -> Color(0xFFE5FFE6)
            else -> Color.Gray

        }
    }
}
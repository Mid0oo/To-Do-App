package com.example.to_doapp.ui

import androidx.compose.ui.graphics.Color


enum class Labels(val labelName: String, val color: Color) {
    Work("Work", Color(0xFFFFF6E7)),
    Finance("Finance",Color(0xFFE5FFE6)),
    Personal("Personal",Color(0xFFF3E4F6)),
    Other("Other",Color(0xFFEFEFEF))
}
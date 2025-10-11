package com.example.to_doapp.data

import androidx.room.TypeConverter
import com.example.to_doapp.ui.Labels

class Converters {
    @TypeConverter
    fun fromLabel(label: Labels): String = label.name

    @TypeConverter
    fun toLabel(name: String): Labels = Labels.valueOf(name)
}
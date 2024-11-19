package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.type.DateTime

@Composable
fun Note(title: String, date: DateTime? = null){
    Row {
        if (date != null){
            Text(text = "${date.day}.${date.month} - ")
        }
        Text(text = title)
    }
}
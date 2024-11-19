package com.example.gostudyapp.core.data.model

import com.google.type.DateTime

data class Note(
    val noteId: Int,
    val title: String,
    val date: DateTime? = null
)

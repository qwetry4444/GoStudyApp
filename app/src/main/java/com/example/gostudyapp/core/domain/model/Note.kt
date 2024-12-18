package com.example.gostudyapp.core.domain.model

import com.google.type.DateTime

data class Note(
    val noteId: Int,
    val title: String,
    val date: DateTime? = null
)

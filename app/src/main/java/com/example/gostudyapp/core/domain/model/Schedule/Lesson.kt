package com.example.gostudyapp.core.domain.model.Schedule

data class Lesson(
    val id: Int,
    val subject: String = "",
    val number: Int,
    val room: String = ""
)

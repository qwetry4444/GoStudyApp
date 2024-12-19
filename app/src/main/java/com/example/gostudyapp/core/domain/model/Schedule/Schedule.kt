package com.example.gostudyapp.core.domain.model.Schedule

data class Schedule(
    val subjectID: String = "",
    val roomID: String = "",
    val weekday: Int = 1,
    val number: Int = 1,
    val weekType: String = ""
)
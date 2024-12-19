package com.example.gostudyapp.core.data.database.entities

data class ScheduleDto(
    val subjectID: String = "",
    val roomID: String = "",
    val weekday: Int = 1,
    val number: Int = 1,
    val weekType: String  = ""
)
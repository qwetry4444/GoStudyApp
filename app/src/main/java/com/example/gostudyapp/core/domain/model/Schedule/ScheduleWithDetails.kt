package com.example.gostudyapp.core.domain.model.Schedule

data class ScheduleWithDetails(
    val weekday: String,
    val number: Int,
    val subject: String,
    val teachers: List<String>,
    val groups: List<String>,
    val room: String,
    val isEvenWeek: Boolean,
    val isOddWeek: Boolean,
    val subgroups: List<String>
)
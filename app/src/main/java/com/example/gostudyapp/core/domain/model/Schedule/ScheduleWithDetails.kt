package com.example.gostudyapp.core.domain.model.Schedule

data class ScheduleWithDetails(
    val weekday: String,
    val number: Int,
    val subject: Subject,
    val teachers: List<Teacher>,
    val groups: List<String>,
    val room: Room,
    val isEvenWeek: Boolean,
    val isOddWeek: Boolean,
    val subgroups: List<String>
)
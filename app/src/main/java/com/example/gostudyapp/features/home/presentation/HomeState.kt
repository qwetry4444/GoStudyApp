package com.example.gostudyapp.features.home.presentation

import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.WeekdayWithDate

data class HomeState(
    val weekdaysWithDate: List<WeekdayWithDate> = listOf(),
    var selectedWeekday: Weekday = Weekday.Monday,
    var userGroupNumber: String = "",
    var currentScheduleList: List<ScheduleWithDetails> = listOf(),
    var fullScheduleList: List<ScheduleWithDetails> = listOf()
)

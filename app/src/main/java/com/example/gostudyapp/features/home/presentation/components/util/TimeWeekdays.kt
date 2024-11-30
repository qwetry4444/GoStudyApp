package com.example.gostudyapp.features.home.presentation.components.util

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

enum class Weekday{
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday,
}

fun WeekdayToString(weekday: Weekday): String {
    return when(weekday){
        Weekday.Monday -> "Пн"
        Weekday.Tuesday -> "Вт"
        Weekday.Wednesday -> "Ср"
        Weekday.Thursday -> "Чт"
        Weekday.Friday -> "Пт"
        Weekday.Saturday -> "Сб"
        Weekday.Sunday -> "Вс"
    }
}


fun getCurrentWeekDates(): Map<Weekday, LocalDate> {
    val currentDate = LocalDate.now()

    val weekFields = WeekFields.of(Locale.getDefault())
    val firstDayOfWeek = weekFields.firstDayOfWeek

    val currentDayOfWeek = currentDate.dayOfWeek
    val daysToSubtract = (currentDayOfWeek.value - firstDayOfWeek.value + 7) % 7
    val startOfWeek = currentDate.minusDays(daysToSubtract.toLong())

    return Weekday.entries.associateWith { weekday -> startOfWeek.plusDays(weekday.ordinal.toLong()) }
}
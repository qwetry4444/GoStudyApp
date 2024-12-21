package com.example.gostudyapp.features.home.presentation.components.util

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

enum class Weekday(val value : Int)
{
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7);

    companion object {
        fun from(findValue: Int): Weekday = entries.first { it.value == findValue }
    }

    override fun toString(): String {
        return super.toString()
    }
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

data class WeekdayWithDate(
    val weekday: Weekday,
    val date: LocalDate
)

fun getCurrentWeekdaysWithDate(): List<WeekdayWithDate> {
    val currentDate = LocalDate.now()

    val weekFields = WeekFields.of(Locale.getDefault())
    val firstDayOfWeek = weekFields.firstDayOfWeek

    val currentDayOfWeek = currentDate.dayOfWeek
    val daysToSubtract = (currentDayOfWeek.value - firstDayOfWeek.value + 7) % 7
    val startOfWeek = currentDate.minusDays(daysToSubtract.toLong())

    return Weekday.entries
        .filter { it != Weekday.Sunday }
        .map { weekday ->
            WeekdayWithDate(
                weekday = weekday,
                date = startOfWeek.plusDays(weekday.ordinal.toLong() + 1)
            )
        }
}

fun getCurrentWeekday(): Weekday {
    return Weekday.from(LocalDate.now().dayOfWeek.value)
}
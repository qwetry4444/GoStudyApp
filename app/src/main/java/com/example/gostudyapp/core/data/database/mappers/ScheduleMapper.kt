package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.ScheduleDto
import com.example.gostudyapp.core.domain.model.Schedule.Schedule

fun ScheduleDto.toDomain(): Schedule {
    return Schedule(
        subjectID = subjectID,
        roomID = roomID,
        weekday = weekday,
        number = number,
        weekType = weekType
    )
}

fun Schedule.toDto(): ScheduleDto {
    return ScheduleDto(
        subjectID = subjectID,
        roomID = roomID,
        weekday = weekday,
        number = number,
        weekType = weekType
    )
}
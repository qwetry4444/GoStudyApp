package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.ScheduleTeacherDto
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleTeacher

fun ScheduleTeacherDto.toDomain(): ScheduleTeacher {
    return ScheduleTeacher(
        scheduleID = scheduleID,
        teacherID = teacherID
    )
}

fun ScheduleTeacher.toDto(): ScheduleTeacherDto {
    return ScheduleTeacherDto(
        scheduleID = scheduleID,
        teacherID = teacherID
    )
}

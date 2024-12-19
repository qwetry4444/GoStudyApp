package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.TeacherDto
import com.example.gostudyapp.core.domain.model.Schedule.Teacher

fun TeacherDto.toDomain(): Teacher {
    return Teacher(
        firstName = firstName,
        lastName = lastName,
        patronymic = patronymic,
        department = department,
        position = position,
        academicDegree = academicDegree
    )
}

fun Teacher.toDto(): TeacherDto {
    return TeacherDto(
        firstName = firstName,
        lastName = lastName,
        patronymic = patronymic,
        department = department,
        position = position,
        academicDegree = academicDegree
    )
}

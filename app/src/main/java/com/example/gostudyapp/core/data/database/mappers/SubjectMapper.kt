package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.SubjectDto
import com.example.gostudyapp.core.domain.model.Schedule.Subject

fun SubjectDto.toDomain(): Subject {
    return Subject(
        subjectName = subjectName
    )
}

fun Subject.toDto(): SubjectDto {
    return SubjectDto(
        subjectName = subjectName
    )
}

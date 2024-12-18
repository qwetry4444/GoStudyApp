package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.GroupDto
import com.example.gostudyapp.core.domain.model.Schedule.Group

fun GroupDto.toDomain(): Group {
    return Group(groupNumber = groupNumber, educationalDirection = educationalDirection)
}

fun Group.toDto(): GroupDto {
    return GroupDto(groupNumber = groupNumber, educationalDirection = educationalDirection)
}
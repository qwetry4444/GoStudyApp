package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.UserDto
import com.example.gostudyapp.core.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        subgroupID = subgroupID
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        uid = uid,
        subgroupID = subgroupID
    )
}
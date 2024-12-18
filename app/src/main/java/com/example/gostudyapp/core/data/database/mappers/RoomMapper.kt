package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.RoomDto
import com.example.gostudyapp.core.domain.model.Schedule.Room

fun RoomDto.toDomain(): Room {
    return Room(roomNumber = roomNumber, building = building)
}

fun Room.toDto(): RoomDto {
    return RoomDto(roomNumber = roomNumber, building = building)
}
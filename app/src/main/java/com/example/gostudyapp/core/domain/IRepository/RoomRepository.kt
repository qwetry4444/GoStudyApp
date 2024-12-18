package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.Room

interface RoomRepository {
    suspend fun getRoomById(roomId: String) : Room?
    suspend fun getAllRooms() : List<Room>
    suspend fun addRoom(room: Room)
}
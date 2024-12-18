package com.example.gostudyapp.core.domain.usecases.RoomUseCase

import com.example.gostudyapp.core.domain.IRepository.RoomRepository
import com.example.gostudyapp.core.domain.model.Schedule.Room

class AddRoomUseCase(private val repository: RoomRepository) {
    suspend operator fun invoke(room: Room) = repository.addRoom(room)
}
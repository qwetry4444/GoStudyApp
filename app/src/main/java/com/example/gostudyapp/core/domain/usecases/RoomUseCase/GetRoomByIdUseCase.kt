package com.example.gostudyapp.core.domain.usecases.RoomUseCase

import com.example.gostudyapp.core.domain.IRepository.RoomRepository
import com.example.gostudyapp.core.domain.model.Schedule.Room

class GetRoomByIdUseCase(private val repository: RoomRepository) {
    suspend operator fun invoke(roomId: String) : Room? {
        return repository.getRoomById(roomId)
    }
}
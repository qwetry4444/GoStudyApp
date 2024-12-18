package com.example.gostudyapp.core.domain.usecases.RoomUseCase

import com.example.gostudyapp.core.domain.IRepository.RoomRepository

class GetAllRoomsUseCase(private val repository: RoomRepository) {
    suspend operator fun invoke() = repository.getAllRooms()
}
package com.example.gostudyapp.core.domain.usecases.ScheduleUseCase

import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository

class GetScheduleByIdUseCase(private val repository: ScheduleRepository) {
    suspend operator fun invoke(scheduleId: String) = repository.getScheduleById(scheduleId)
}
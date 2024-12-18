package com.example.gostudyapp.core.domain.usecases.ScheduleUseCase

import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository
import com.example.gostudyapp.core.domain.model.Schedule.Schedule

class AddScheduleUseCase(private val scheduleRepository: ScheduleRepository) {
    suspend operator fun invoke(schedule: Schedule) = scheduleRepository.addSchedule(schedule)
}
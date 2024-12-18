package com.example.gostudyapp.core.domain.usecases.ScheduleSubgroupUseCase

import com.example.gostudyapp.core.domain.IRepository.ScheduleSubgroupRepository
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleSubgroup

class AddScheduleSubgroupUseCase(private val repository: ScheduleSubgroupRepository) {
    suspend operator fun invoke(scheduleSubgroup: ScheduleSubgroup) = repository.addScheduleSubgroup(scheduleSubgroup)
}
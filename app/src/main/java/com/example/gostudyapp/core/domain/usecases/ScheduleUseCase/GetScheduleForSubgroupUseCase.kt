package com.example.gostudyapp.core.domain.usecases.ScheduleUseCase

import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository

class GetScheduleForSubgroupUseCase(private val repository: ScheduleRepository) {
    suspend operator fun invoke(subgroupId: String) = repository.getScheduleForSubgroup(subgroupId)
}
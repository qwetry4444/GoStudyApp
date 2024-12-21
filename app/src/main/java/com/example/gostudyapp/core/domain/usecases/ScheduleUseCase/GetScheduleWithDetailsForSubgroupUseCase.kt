package com.example.gostudyapp.core.domain.usecases.ScheduleUseCase

import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository

class GetScheduleWithDetailsForSubgroupUseCase(private val repository: ScheduleRepository) {
    suspend operator fun invoke(subgroupId: String) = repository.getScheduleWithDetailsForSubgroup(subgroupId)
}
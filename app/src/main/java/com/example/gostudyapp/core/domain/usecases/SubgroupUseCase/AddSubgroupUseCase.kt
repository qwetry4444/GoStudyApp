package com.example.gostudyapp.core.domain.usecases.SubgroupUseCase

import com.example.gostudyapp.core.domain.IRepository.SubgroupRepository
import com.example.gostudyapp.core.domain.model.Schedule.Subgroup

class AddSubgroupUseCase(private val subgroupRepository: SubgroupRepository) {
    suspend operator fun invoke(subgroup: Subgroup) = subgroupRepository.addSubgroup(subgroup)
}
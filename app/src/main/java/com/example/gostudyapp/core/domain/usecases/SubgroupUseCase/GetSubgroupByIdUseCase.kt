package com.example.gostudyapp.core.domain.usecases.SubgroupUseCase

import com.example.gostudyapp.core.domain.IRepository.SubgroupRepository

class GetSubgroupByIdUseCase(private val repository: SubgroupRepository) {
    suspend operator fun invoke(subgroupId: String) = repository.getSubgroupById(subgroupId)
}
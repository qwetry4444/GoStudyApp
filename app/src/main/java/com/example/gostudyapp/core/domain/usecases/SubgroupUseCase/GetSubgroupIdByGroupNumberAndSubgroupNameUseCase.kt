package com.example.gostudyapp.core.domain.usecases.SubgroupUseCase

import com.example.gostudyapp.core.domain.IRepository.SubgroupRepository


class GetSubgroupIdByGroupNumberAndSubgroupNameUseCase(private val repository: SubgroupRepository) {
    suspend operator fun invoke(groupNumber: String, subgroupName: String) =
        repository.getSubgroupIdByGroupNumberAndSubgroupName(groupNumber, subgroupName)
}
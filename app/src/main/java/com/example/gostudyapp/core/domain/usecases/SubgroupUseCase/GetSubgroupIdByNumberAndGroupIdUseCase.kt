package com.example.gostudyapp.core.domain.usecases.SubgroupUseCase

import com.example.gostudyapp.core.domain.IRepository.SubgroupRepository


class GetSubgroupIdByNumberAndGroupIdUseCase(private val repository: SubgroupRepository) {
    suspend operator fun invoke(subgroupNumber: String, groupId: String) =
        repository.getSubgroupIdByNumberAndGroupId(subgroupNumber, groupId)
}
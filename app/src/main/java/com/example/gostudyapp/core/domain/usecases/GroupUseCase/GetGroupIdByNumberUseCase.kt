package com.example.gostudyapp.core.domain.usecases.GroupUseCase

import com.example.gostudyapp.core.domain.IRepository.GroupRepository

class GetGroupIdByNumberUseCase(private val repository: GroupRepository) {
    suspend operator fun invoke(groupId: String) = repository.getGroupIdByNumber(groupId)
}
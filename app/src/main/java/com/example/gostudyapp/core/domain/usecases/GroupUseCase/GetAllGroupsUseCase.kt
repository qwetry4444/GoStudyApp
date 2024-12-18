package com.example.gostudyapp.core.domain.usecases.GroupUseCase

import com.example.gostudyapp.core.domain.IRepository.GroupRepository

class GetAllGroupsUseCase(private val repository: GroupRepository) {
    suspend operator fun invoke() = repository.getAllGroups()
}
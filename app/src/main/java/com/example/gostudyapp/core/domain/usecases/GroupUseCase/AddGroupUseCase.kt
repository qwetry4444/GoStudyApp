package com.example.gostudyapp.core.domain.usecases.GroupUseCase

import com.example.gostudyapp.core.domain.IRepository.GroupRepository
import com.example.gostudyapp.core.domain.model.Schedule.Group

class AddGroupUseCase(private val groupRepository: GroupRepository) {
    suspend operator fun invoke(group: Group) = groupRepository.addGroup(group)
}
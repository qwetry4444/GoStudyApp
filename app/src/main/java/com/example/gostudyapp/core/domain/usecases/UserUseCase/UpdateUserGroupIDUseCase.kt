package com.example.gostudyapp.core.domain.usecases.UserUseCase

import com.example.gostudyapp.core.domain.IRepository.UserRepository
import javax.inject.Inject

class UpdateUserGroupIDUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(uid: String, newSubgroupID: String) = repository.updateUserSubgroupID(uid, newSubgroupID)
}
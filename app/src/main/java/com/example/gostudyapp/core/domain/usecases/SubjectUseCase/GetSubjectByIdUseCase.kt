package com.example.gostudyapp.core.domain.usecases.SubjectUseCase

import com.example.gostudyapp.core.domain.IRepository.SubjectRepository

class GetSubjectByIdUseCase(private val repository: SubjectRepository) {
    suspend operator fun invoke(subjectId: String) = repository.getSubjectById(subjectId)
}
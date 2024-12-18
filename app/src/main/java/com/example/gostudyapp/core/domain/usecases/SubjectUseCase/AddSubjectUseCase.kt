package com.example.gostudyapp.core.domain.usecases.SubjectUseCase

import com.example.gostudyapp.core.domain.IRepository.SubjectRepository
import com.example.gostudyapp.core.domain.model.Schedule.Subject

class AddSubjectUseCase(private val subjectRepository: SubjectRepository) {
    suspend operator fun invoke(subject: Subject) = subjectRepository.addSubject(subject)
}
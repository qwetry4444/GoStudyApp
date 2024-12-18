package com.example.gostudyapp.core.domain.usecases.TeacherUseCase

import com.example.gostudyapp.core.domain.IRepository.TeacherRepository

class GetAllTeachersUseCase(private val repository: TeacherRepository) {
    suspend operator fun invoke() = repository.getAllTeachers()
}
package com.example.gostudyapp.core.domain.usecases.TeacherUseCase

import com.example.gostudyapp.core.domain.IRepository.TeacherRepository

class GetTeacherByIdUseCase(private val repository: TeacherRepository) {
    suspend operator fun invoke(teacherId: String) = repository.getTeacherById(teacherId)
}
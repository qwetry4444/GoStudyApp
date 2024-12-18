package com.example.gostudyapp.core.domain.usecases.TeacherUseCase

import com.example.gostudyapp.core.domain.IRepository.TeacherRepository
import com.example.gostudyapp.core.domain.model.Schedule.Teacher

class AddTeacherUseCase(private val teacherRepository: TeacherRepository) {
    suspend operator fun invoke(teacher: Teacher) = teacherRepository.addTeacher(teacher)
}
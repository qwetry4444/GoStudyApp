package com.example.gostudyapp.core.domain.usecases.ScheduleTeacherUseCase

import com.example.gostudyapp.core.domain.IRepository.ScheduleTeacherRepository
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleTeacher

class AddScheduleTeacherUseCase(private val repository: ScheduleTeacherRepository) {
    suspend operator fun invoke(scheduleTeacher: ScheduleTeacher) = repository.addScheduleTeacher(scheduleTeacher)
}
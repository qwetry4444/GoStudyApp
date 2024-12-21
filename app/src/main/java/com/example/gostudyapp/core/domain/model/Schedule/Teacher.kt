package com.example.gostudyapp.core.domain.model.Schedule

class Teacher(
    val firstName: String = "",
    val lastName: String = "",
    val patronymic : String = "",
    val department: String = "",
    val position: String = "",
    val academicDegree: String = ""
) {
    override fun toString(): String {
        return "${firstName.substring(0, 1)}. ${patronymic.substring(0, 1)}. ${lastName}"
    }
}
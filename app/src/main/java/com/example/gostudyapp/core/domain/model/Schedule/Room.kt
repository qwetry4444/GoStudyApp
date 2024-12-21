package com.example.gostudyapp.core.domain.model.Schedule

class Room(
    val roomNumber: String = "",
    val building: String = ""
){
    override fun toString(): String {
        return "$building$roomNumber"
    }
}

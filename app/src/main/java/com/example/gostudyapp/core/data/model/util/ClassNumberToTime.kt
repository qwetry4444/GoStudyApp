package com.example.gostudyapp.core.data.model.util

fun ClassNumberToTime(number: Int) : String{
    return when(number){
        1 -> "8:30 - 9:50"
        2 -> "10:00 - 11:20"
        3 -> "11:30 - 12:50"
        4 -> "13:20 - 14:40"
        5 -> "14:50 - 16:10"
        6 -> "16:20 - 17:40"
        7 -> "18:00 - 19:20"
        8 -> "19:30 - 20:50"
        else -> "-"
    }
}
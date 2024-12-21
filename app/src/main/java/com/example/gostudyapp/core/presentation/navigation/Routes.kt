package com.example.gostudyapp.core.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Route{
    @Serializable
    data object SignIn : Route()

    @Serializable
    data object SignUp : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data object Profile: Route()

    @Serializable
    data class AboutTeacher(
        val teacherId: String
    ) : Route()

    @Serializable
    data class Schedule (
        val studyGroup: String
    ) : Route()
}




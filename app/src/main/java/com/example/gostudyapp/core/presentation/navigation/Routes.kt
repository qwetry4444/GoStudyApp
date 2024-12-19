package com.example.gostudyapp.core.presentation.navigation

import kotlinx.serialization.Serializable

//sealed class Route(val route: String) {
//    object SignIn : Route("sign_in")
//    object SignUp : Route("sign_up")
//    object Home : Route("home")
//    object Profile : Route("profile")
//    data class AboutTeacher(val teacherId: String) : Route("about_teacher/{teacherId}") {
//        fun createRoute(teacherId: String) = "about_teacher/$teacherId"
//    }
//    data class Schedule(val studyGroup: String) : Route("schedule/{studyGroup}") {
//        fun createRoute(studyGroup: String) = "schedule/$studyGroup"
//    }
//}

@Serializable
sealed class Route{
    @Serializable
    object SignIn : Route()
    @Serializable
    object SignUp : Route()
    @Serializable
    object Home : Route()
    @Serializable
    object Profile: Route()

    @Serializable
    data class AboutTeacher(
        val teacherId: String
    ) : Route()

    @Serializable
    data class Schedule (
        val studyGroup: String
    ) : Route()

}




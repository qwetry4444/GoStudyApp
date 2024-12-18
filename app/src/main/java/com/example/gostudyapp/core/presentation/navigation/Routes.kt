package com.example.gostudyapp.core.presentation.navigation

import kotlinx.serialization.Serializable

//@Serializable
//sealed class Routes {
//    @Serializable
//    object SignIn
//    @Serializable
//    object SignUp
//    @Serializable
//    object GoogleSignIn
//    @Serializable
//    object GoogleSignUp
//    @Serializable
//    object Home
//    @Serializable
//    data class ScheduleDto(
//        val studyGroup: String
//    )
//}

@Serializable
open class Route

@Serializable
object SignIn : Route()
@Serializable
object SignUp : Route()
@Serializable
object GoogleSignIn : Route()
@Serializable
object GoogleSignUp : Route()
@Serializable
object Home : Route()

@Serializable
data class Schedule (
    val studyGroup: String
) : Route()

//sealed class Screen(val route: String) {
//    object SignIn : Screen("signIn")
//    object SignUp : Screen("signUp")
//    object GoogleSignIn : Screen("googleSignIn")
//
//    object Home : Screen("home")
//
//    object ScheduleDto : Screen("schedule/{$ARG_STUDY_GROUP_URI}") {
//        fun createRoute(scheduleUri: String) = "schedule/$scheduleUri"
//    }
//
//    companion object {
//        val ARG_STUDY_GROUP_URI = "studyGroupUri"
//    }
//}
package com.example.gostudyapp.core.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.gostudyapp.GoStudyAppState
import com.example.gostudyapp.core.presentation.navigation.Route.SignIn
import com.example.gostudyapp.features.aboutTeacher.presentation.AboutTeacherPage
import com.example.gostudyapp.features.auth.presentation.SignIn.LogInPage
import com.example.gostudyapp.features.auth.presentation.SignUp.SignUpPage
import com.example.gostudyapp.features.home.presentation.HomePage
import com.example.gostudyapp.features.profile.Presentation.ProfilePage

fun NavGraphBuilder.navGraph(appState: GoStudyAppState) {
    composable<SignIn> {
        LogInPage(
            navigateOnSignIn = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            navigateOnSignUp = { route -> appState.navigate(route) }
        )
    }

    composable<Route.SignUp>{
        SignUpPage(
            navigateOnSignIn = { route -> appState.navigate(route) },
            navigateOnSignUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable<Route.Home>{
        HomePage()
    }

    composable<Route.Profile> {
        ProfilePage(navigateOnSignOut = { route -> appState.navigate(route) })
    }

    composable<Route.AboutTeacher>{
        AboutTeacherPage()
    }
}
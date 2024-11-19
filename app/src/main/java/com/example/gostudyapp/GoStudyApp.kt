package com.example.gostudyapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gostudyapp.core.presentation.navigation.Home
import com.example.gostudyapp.core.presentation.navigation.SignIn
import com.example.gostudyapp.core.presentation.navigation.SignUp
import com.example.gostudyapp.features.auth.presentation.SignIn.LogInPage
import com.example.gostudyapp.features.auth.presentation.SignUp.SignUpPage
import com.example.gostudyapp.features.home.presentation.HomePage

@Composable
fun GoStudyApp(
    appState: GoStudyAppState = rememberGoStudyAppState()
) {
    val navController = appState.navController
    NavHost(
            navController = navController,
            startDestination = Home
        ) {
            notesGraph(appState)
        }
}

fun NavGraphBuilder.notesGraph(appState: GoStudyAppState) {
    composable<SignIn> {
        LogInPage(
            navigateOnSignIn = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            navigateOnSignUp = { route -> appState.navigate(route) }
        )
    }

    composable<SignUp>{
        SignUpPage(
            navigateOnSignIn = {route -> appState.navigate(route)},
            navigateOnSignUp = {route, popUp -> appState.navigateAndPopUp(route, popUp)})
    }
    composable<Home>{
        HomePage()
    }

}
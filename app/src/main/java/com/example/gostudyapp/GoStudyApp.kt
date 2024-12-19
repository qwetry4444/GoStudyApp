package com.example.gostudyapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gostudyapp.core.presentation.components.BottomNavigationBar
import com.example.gostudyapp.core.presentation.components.HeaderComponent
import com.example.gostudyapp.core.presentation.navigation.Route
import com.example.gostudyapp.core.presentation.navigation.Route.SignIn
import com.example.gostudyapp.features.aboutTeacher.presentation.AboutTeacherPage
import com.example.gostudyapp.features.auth.presentation.SignIn.LogInPage
import com.example.gostudyapp.features.auth.presentation.SignUp.SignUpPage
import com.example.gostudyapp.features.home.presentation.HomePage
import com.example.gostudyapp.features.profile.Presentation.ProfilePage

@Composable
fun GoStudyApp(
    appState: GoStudyAppState = rememberGoStudyAppState()
) {
    val navController = appState.navController

    Scaffold(
        topBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute != SignIn.serializer().descriptor.serialName && currentRoute != Route.SignUp.serializer().descriptor.serialName){
                HeaderComponent()
            }
        },
        bottomBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute != SignIn.serializer().descriptor.serialName && currentRoute != Route.SignUp.serializer().descriptor.serialName){
                BottomNavigationBar(navController = navController)
            }
        }
    )
    { innerPadding ->
        Column() {
            NavHost(
                navController = navController,
                startDestination = Route.SignUp,
                modifier = Modifier.padding(innerPadding)
            ) { notesGraph(appState) }
        }
    }
}

fun NavGraphBuilder.notesGraph(appState: GoStudyAppState) {
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
        ProfilePage()
    }

    composable<Route.AboutTeacher>{
        AboutTeacherPage()
    }
}
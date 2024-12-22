package com.example.gostudyapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gostudyapp.core.presentation.components.BottomNavigationBar
import com.example.gostudyapp.core.presentation.components.HeaderComponent
import com.example.gostudyapp.core.presentation.navigation.Route
import com.example.gostudyapp.core.presentation.navigation.Route.SignIn
import com.example.gostudyapp.core.presentation.navigation.navGraph

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
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding)
        ) { navGraph(appState) }
    }
}


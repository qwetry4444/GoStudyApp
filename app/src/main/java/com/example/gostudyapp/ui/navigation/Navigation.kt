package com.example.gostudyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gostudyapp.AuthViewModel
import com.example.gostudyapp.ui.pages.SignIn.LogInPage
import com.example.gostudyapp.ui.pages.SignIn.LogInViewModel

@Composable
fun Navigation(modifier: Modifier, logInViewModel: LogInViewModel, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LogInPage(modifier, navController, logInViewModel, authViewModel)
        }
    } )
}


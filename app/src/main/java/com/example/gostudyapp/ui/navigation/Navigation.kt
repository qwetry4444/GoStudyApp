package com.example.gostudyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gostudyapp.AuthViewModel
import com.example.gostudyapp.ui.pages.SignIn.LogInPage
import com.example.gostudyapp.ui.pages.SignIn.LogInViewModel

//@Composable
//fun Navigation(modifier: Modifier) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login", builder = {
//        composable<SignIn> {
//            val logInViewModel: LogInViewModel = viewModel()
//            val authViewModel: AuthViewModel = viewModel()
//
//            LogInPage(navController, logInViewModel, authViewModel)
//        }
//
//        composable<GoogleSignIn> {
//
//        }
//    } )
//}


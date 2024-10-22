package com.example.gostudyapp.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gostudyapp.ui.navigation.GoogleSignIn
import com.example.gostudyapp.ui.navigation.SignIn
import com.example.gostudyapp.ui.pages.GoogleSignIn.GoogleSignInPage
import com.example.gostudyapp.ui.pages.SignIn.LogInPage

@Composable
fun GoStudyApp(
    appState: GoStudyAppState = rememberGoStudyAppState()
) {
    //if (appState.isOnline) {
        val navController = appState.navController

        NavHost(
            navController = navController,
            startDestination = GoogleSignIn
        ) {
            composable<SignIn> {
                LogInPage(navController)
            }

            composable<GoogleSignIn> {
                GoogleSignInPage(navController, hiltViewModel())
            }
        }
//    } else {
//        OfflineDialog {
//            appState.refreshOnline()
//        }
//    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { },
        text = { },
        confirmButton = {
            TextButton(onClick = onRetry) {

            }
        }
    )
}

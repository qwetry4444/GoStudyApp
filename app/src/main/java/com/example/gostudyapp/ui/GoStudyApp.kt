package com.example.gostudyapp.ui

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gostudyapp.core.presentation.navigation.GoogleSignIn
import com.example.gostudyapp.core.presentation.navigation.Home
import com.example.gostudyapp.core.presentation.navigation.SignIn
import com.example.gostudyapp.features.auth.presentation.GoogleSignIn.GoogleSignInPage
import com.example.gostudyapp.features.auth.presentation.SignIn.LogInPage
import com.example.gostudyapp.features.home.presentation.HomePage
import androidx.lifecycle.lifecycleScope

@Composable
fun GoStudyApp(
    appState: GoStudyAppState = rememberGoStudyAppState()
) {
        val navController = appState.navController
        val googleAuthUiClient = appState.googleAuthUiClient

        NavHost(
            navController = navController,
            startDestination = SignIn
        ) {
            composable<SignIn> {
                LaunchedEffect(key1 = Unit) {
                    if(googleAuthUiClient.getSignedInUser() != null) {
                        navController.navigate(Home)
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if(result.resultCode == RESULT_OK) {

                            lifecycleScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if(state.isSignInSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("profile")
                        viewModel.resetState()
                    }
                }

                LogInPage(navController)
            }

            composable<GoogleSignIn> {
                GoogleSignInPage(navController, hiltViewModel())
            }
            composable<Home>{
                HomePage(navController)
            }


        }
}

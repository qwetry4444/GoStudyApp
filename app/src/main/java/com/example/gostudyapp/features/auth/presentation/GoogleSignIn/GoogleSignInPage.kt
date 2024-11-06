package com.example.gostudyapp.features.auth.presentation.GoogleSignIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun GoogleSignInPage(
    navController: NavController,
    googleSignInViewModel: GoogleSignInViewModel
) {
    Column(modifier = Modifier.padding(64.dp)) {
        Text(text = "123123")
    }
}
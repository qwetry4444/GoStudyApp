package com.example.gostudyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.gostudyapp.ui.AuthViewModel
import com.example.gostudyapp.ui.GoStudyApp
import com.example.gostudyapp.ui.theme.GoStudyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GoStudyAppTheme {
                GoStudyApp()
            }
        }
    }
}
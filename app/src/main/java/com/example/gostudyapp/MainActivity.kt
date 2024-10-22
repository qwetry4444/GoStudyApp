package com.example.gostudyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gostudyapp.ui.GoStudyApp
import com.example.gostudyapp.ui.theme.GoStudyAppTheme

class MainActivity : ComponentActivity() {
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
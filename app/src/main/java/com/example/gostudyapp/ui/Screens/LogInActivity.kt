package com.example.gostudyapp.ui.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gostudyapp.R
import com.example.gostudyapp.ui.Screens.ui.theme.GoStudyAppTheme

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoStudyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(16.dp))
    {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.contentDescriptionLogo),
                alignment = Alignment.Center,

            )

            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = stringResource(id = R.string.placeholderLogin))}
            )

            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = stringResource(id = R.string.placeholderPassword))}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GoStudyAppTheme {
        Greeting2("Android")
    }
}
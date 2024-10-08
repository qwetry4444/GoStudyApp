package com.example.gostudyapp.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gostudyapp.AuthViewModel
import com.example.gostudyapp.R
import com.example.gostudyapp.ui.pages.ui.theme.GoStudyAppTheme
import com.example.gostudyapp.ui.theme.ButtonGradientLeft
import com.example.gostudyapp.ui.theme.ButtonGradientRight



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    Box(modifier = Modifier.padding(56.dp))
    {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.contentDescriptionLogo),
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(96.dp))

            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)),

                placeholder = { Text(text = stringResource(id = R.string.placeholderLogin)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                placeholder = { Text(text = stringResource(id = R.string.placeholderPassword))}
            )
            
            Spacer(modifier = Modifier.height(96.dp))

            Box(modifier = Modifier
                .size(width = 300.dp, height = 52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            ButtonGradientLeft,
                            ButtonGradientRight
                        )
                    )
                )
            )
            {
                Button(
                    onClick = { },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxSize()
                )
                {
                    Text(text = stringResource(id = R.string.buttonTextLogIn))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = stringResource(id = R.string.textViewAuthWith))

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier
                .size(width = 300.dp, height = 42.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
            )
            {
                Button(
                    onClick = { },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxSize()
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.gmail_logo),
                        contentDescription = stringResource(id = R.string.contentDescriptionGmainLogo)
                    )
                    Text(text = stringResource(id = R.string.buttonTextSurGUMail))
                }
            }
        }
    }
}

package com.example.gostudyapp.ui.pages.SignIn

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.gostudyapp.ui.theme.ButtonGradientLeft
import com.example.gostudyapp.ui.theme.ButtonGradientRight



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInPage(modifier: Modifier = Modifier, navController: NavController, logInViewModel: LogInViewModel, authViewModel: AuthViewModel) {

    val logInState = logInViewModel.uiState.collectAsState()

    Box(modifier = Modifier
        .padding(56.dp)
        .fillMaxSize())
    {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.gostudy_logo),
                contentDescription = stringResource(id = R.string.contentDescriptionLogo),
                modifier = Modifier.size(width = 256.dp, height = 194.dp),
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(78.dp))

            TextField(
                value = logInState.value.currentEmail,
                onValueChange = { logInViewModel.onEmailInputChanged(it) },
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
                value = logInState.value.currentPassword,
                onValueChange = { logInViewModel.onEmailInputChanged(it) },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                placeholder = { Text(text = stringResource(id = R.string.placeholderPassword))}
            )
            
            Spacer(modifier = Modifier.height(52.dp))

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
                        contentDescription = stringResource(id = R.string.contentDescriptionGmailLogo)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = stringResource(id = R.string.buttonTextSurGUMail))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun LoginPagePreview(){
//    val authViewModel: AuthViewModel by viewModels()
//    val navController = rememberNavController()
//    LogInPage(Modifier, navController, authViewModel)
}

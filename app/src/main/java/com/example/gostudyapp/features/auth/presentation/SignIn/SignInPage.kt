package com.example.gostudyapp.features.auth.presentation.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gostudyapp.R
import com.example.gostudyapp.core.presentation.navigation.Route
import com.example.gostudyapp.features.auth.presentation.components.GoogleAuthButton
import com.example.gostudyapp.ui.theme.ButtonGradientLeft
import com.example.gostudyapp.ui.theme.ButtonGradientRight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInPage(
    navigateOnSignIn: (Route, Route) -> Unit,
    navigateOnSignUp: (Route) -> Unit,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val logInState = signInViewModel.uiState.collectAsState()

    Box(modifier = Modifier
        .padding(56.dp)
        .fillMaxSize())
    {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.gostudy_logo_with_title),
                contentDescription = stringResource(id = R.string.contentDescriptionLogo),
                modifier = Modifier.size(width = 256.dp, height = 194.dp),
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(78.dp))


            TextField(
                value = logInState.value.currentEmail,
                onValueChange = { signInViewModel.onEmailInputChanged(it) },
                
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = { Text(text = stringResource(id = R.string.placeholderLogin)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = logInState.value.currentPassword,
                onValueChange = { signInViewModel.onPasswordInputChanged(it) },
                visualTransformation = PasswordVisualTransformation(),
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
                    onClick = { signInViewModel.onSignInClick(navigateOnSignIn) },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxSize()
                )
                {
                    Text(text = stringResource(id = R.string.buttonTextLogIn))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = stringResource(id = R.string.stillDontHaveAnAccount), modifier = Modifier)
                Text(text = stringResource(id = R.string.SignUp),
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                    .clickable(onClick = {navigateOnSignUp(Route.SignUp)})
                )
            }
            Spacer(modifier = Modifier.height(64.dp))

            Text(text = stringResource(id = R.string.textViewAuthWith))

            Spacer(modifier = Modifier.height(12.dp))

            GoogleAuthButton{ credential ->
                signInViewModel.onSignInWithGoogle(credential, navigateOnSignIn)
            }
        }
    }
}


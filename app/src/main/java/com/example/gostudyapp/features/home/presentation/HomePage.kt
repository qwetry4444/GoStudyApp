package com.example.gostudyapp.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gostudyapp.R

@Composable
fun HomePage(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier.padding(42.dp)) {
        Column {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.contentDescriptionLogo)
                )
                Text(text = stringResource(id = R.string.homePageTitle))
            }

            Box(modifier = Modifier){
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = stringResource(id = R.string.contentDescriptionProfile)
                )
                Text(text = stringResource(id = R.string.your_profile))
            }

            Column {
                Row {
                    Text(text = stringResource(id = R.string.notes))
                    Image(
                        painter = painterResource(id = R.drawable.notes),
                        contentDescription = stringResource(id = R.string.notesDescriptionProfile)
                    )
                }
            }
        }
    }

}
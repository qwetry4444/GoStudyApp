package com.example.gostudyapp.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gostudyapp.R

@Composable
fun HeaderComponent(modifier: Modifier = Modifier){
    Box(contentAlignment = Alignment.BottomStart,
        modifier = modifier
            .background(
                Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.headerGradientStart),
                        colorResource(id = R.color.headerGradientEnd)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .fillMaxWidth()
            .height(132.dp)

    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 22.dp)
                .fillMaxWidth())
        {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.contentDescriptionLogo),
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.homePageTitle),
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.search_loop), contentDescription = "")
                Spacer(modifier = Modifier.width(22.dp))
                Image(painter = painterResource(id = R.drawable.settings_gear), contentDescription = "")
            }
        }
    }
}
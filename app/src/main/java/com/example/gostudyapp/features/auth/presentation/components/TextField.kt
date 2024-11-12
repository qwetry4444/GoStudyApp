package com.example.gostudyapp.features.auth.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun FormTextField(_value: String, _placeholder: Int, _onValueChange: (String) -> Unit){
    TextField(
        value = _value,
        onValueChange = _onValueChange,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp)),
        placeholder = { Text(text = stringResource(id = _placeholder)) }

    )
}
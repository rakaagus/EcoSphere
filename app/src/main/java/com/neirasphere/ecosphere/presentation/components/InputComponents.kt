package com.neirasphere.ecosphere.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey

@Composable
fun AuthForm(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = BlackColor
            )
        },
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        )
    )
}

@Composable
fun EditProfileForm(
    label: String,
    value: String,
    onValueChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange },
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = BlackColor
            )
        },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        )
    )
}
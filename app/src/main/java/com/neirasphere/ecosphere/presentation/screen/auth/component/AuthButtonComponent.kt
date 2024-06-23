package com.neirasphere.ecosphere.presentation.screen.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neirasphere.ecosphere.ui.theme.InActiveColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun ButtonAuth(
    label: String,
    isDisable: Boolean,
    click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = MaterialTheme.shapes.medium,
        enabled = isDisable,
        onClick = click,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = InActiveColor,
            disabledContentColor = PrimaryColor
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = NeutralColorWhite)
    }
}

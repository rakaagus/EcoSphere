package com.neirasphere.ecosphere.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun AuthWith(
    string: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Divider(
            color = BlackColor, // Warna garis
            thickness = 1.dp, // Ketebalan garis
            modifier = Modifier.weight(1f) // Padding
        )
        Text(text = stringResource(id = string), style = MaterialTheme.typography.bodySmall, color = BlackColor, modifier = Modifier.padding(horizontal = 8.dp))
        Divider(
            color = BlackColor, // Warna garis
            thickness = 1.dp, // Ketebalan garis
            modifier = Modifier.weight(1f) // Padding
        )
    }
}
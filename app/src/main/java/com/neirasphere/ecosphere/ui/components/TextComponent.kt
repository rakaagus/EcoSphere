package com.neirasphere.ecosphere.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun SectionTextColumn(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = title), style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(bottom = 15.dp)
        )
        content()
    }
}

@Composable
fun SectionTextColumnProfile(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = title), style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(bottom = 10.dp)
        )
        content()
    }
}


@Composable
fun SectionProfile(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .clickable {
                navigateTo()
            }
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = title),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.labelSmall,
            color = BlackColor,
            modifier = Modifier.weight(1f)
        )
    }
}
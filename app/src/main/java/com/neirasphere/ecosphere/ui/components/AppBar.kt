package com.neirasphere.ecosphere.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun HomeAppBar(
    name: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.example_image_user),
            contentDescription = "UserImage",
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.hello_user, name),
                color = BlackColor,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 12.sp
                )
            )
            Text(
                text = location,
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.notif_icon),
                contentDescription = "Notif",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
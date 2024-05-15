package com.neirasphere.ecosphere.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite

@Composable
fun ButtonAuth(
    label: String,
    navHostController: NavHostController,
    click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(shape = MaterialTheme.shapes.medium, onClick = click,modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .height(50.dp)) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = NeutralColorWhite)
    }
}

@Composable
fun RoundedIconButton(
    imageId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .size(50.dp), // Ukuran tombol bulat
        content = {
            Image(
                painter = painterResource(id = imageId), // Gambar di dalam tombol
                contentDescription = null // Deskripsi konten kosong karena gambar akan berfungsi sebagai tombol
            )
        },
        colors = IconButtonDefaults.filledIconButtonColors(NeutralColorGrey)
    )
}

@Composable
fun ButtonProfile(
    label: String,
    navHostController: NavHostController,
    click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE22F2F)
        ),
        onClick = click,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp))
    {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }
}
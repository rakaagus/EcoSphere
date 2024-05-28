package com.neirasphere.ecosphere.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun ButtonAuth(
    label: String,
    navHostController: NavHostController,
    click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(shape = MaterialTheme.shapes.medium, onClick = click, modifier = modifier
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
                contentDescription = null, // Deskripsi konten kosong karena gambar akan berfungsi sebagai tombol
                modifier = Modifier.size(25.dp)
            )
        },
        colors = IconButtonDefaults.filledIconButtonColors(NeutralColorGrey)
    )
}

@Composable
fun ButtonEducation(
    label : String,
    onClick : () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryColor
){
    Card(
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = modifier
            .padding(16.dp)
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Row{
            Image(
                painterResource(id = R.drawable.back_button_left),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material.Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = NeutralColorWhite,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painterResource(id = R.drawable.back_button_right),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onClick() }
            )
        }
    }  
}

@Composable
fun ButtonProfile(
    label: String,
    isLogoutButton: Boolean,
    click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isLogoutButton) Color(0xFFE22F2F) else PrimaryColor
        ),
        onClick = click,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp)
    )
    {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }
}
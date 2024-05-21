package com.neirasphere.ecosphere.ui.screen.interactivemap.confirmMaps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun ConfirmMapScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.google_maps),
            contentDescription = "Google Maps",
            modifier = Modifier.size(width = 90.dp, height = 110.dp)
        )
        Text(
            text = "Whats is Your Location?",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color(0xFF3D3D3D)
        )
        Text(
            text = "We need to know your location in order to suggest\n" +
                    "nearbe services",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color(0xFF969696)
        )
        Spacer(modifier = Modifier.height(37.dp))
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.MapScreen.route)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            modifier = Modifier.size(width = 250.dp, height = 40.dp)
        ) {
            Text(
                text = "Allow Location Access",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.SearchMapScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF969696)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(width = 250.dp, height = 40.dp)
        ) {
            Text(
                text = "Enter Location Manually",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}
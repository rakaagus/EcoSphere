package com.neirasphere.ecosphere.ui.screen.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostingScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var text by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Batalkan",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .clickable(
                        onClick = {
                            showBottomSheet = true
                        }
                    )
            )
            Button(
                onClick = {
                          navController.navigate(Screen.DummyDetailPostScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Posting",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = modifier
                    .size(50.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.example_image_user),
                contentScale = ContentScale.Crop,
                contentDescription = "Post User Avatar"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = NeutralColorWhite,
                    focusedContainerColor = NeutralColorWhite,
                    unfocusedIndicatorColor = Color.White
                ),
                placeholder = {
                    Text(text = "Tulis di sini")
                },
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            modifier = modifier,
            sheetState = sheetState
        ) {
            Column(
                modifier = modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                    Text(
                        text = "Hapus",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.BorderColor,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                    Text(
                        text = "Simpan Draf",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Button(
                    onClick = { navController.navigateUp() },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Text(
                        text = "Batalkan",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}
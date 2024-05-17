package com.neirasphere.ecosphere.ui.screen.profile.edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.components.HeaderProfile

@Composable
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    HeaderProfile(
        image = R.drawable.example_image_user,
        name = "Erlin",
        location = "Piyungan, Yogyakarta"
    )
}
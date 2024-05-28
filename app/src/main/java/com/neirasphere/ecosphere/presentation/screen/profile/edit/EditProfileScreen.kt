package com.neirasphere.ecosphere.presentation.screen.profile.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.ButtonProfile
import com.neirasphere.ecosphere.presentation.components.EditProfileForm
import com.neirasphere.ecosphere.presentation.components.HeaderEditProfile
import com.neirasphere.ecosphere.presentation.components.SectionTextColumnProfile

@Composable
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        EditProfileContent(navController = navController)
    }
}

@Composable
fun EditProfileContent(navController: NavController, modifier: Modifier = Modifier) {
    HeaderEditProfile(
        image = R.drawable.example_image_user,
    )
    Spacer(modifier = Modifier.height(20.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_1) {
        EditProfileForm(
            label = stringResource(id = R.string.form_edit_label_1),
            value = "Erlin",
            onValueChange = { })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_2) {
        EditProfileForm(
            label = stringResource(id = R.string.title_email),
            value = "erlinmarbella@gmail.com ",
            onValueChange = { })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_3) {
        EditProfileForm(
            label = stringResource(id = R.string.form_edit_date),
            value = "04 Februari 2002",
            onValueChange = { })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_4) {
        EditProfileForm(
            label = stringResource(id = R.string.title_password),
            value = "",
            onValueChange = { })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_5) {
        EditProfileForm(
            label = stringResource(id = R.string.form_edit_loc),
            value = "Piyungan, Yogyakarta",
            onValueChange = { })
    }
    ButtonProfile(
        label = "Save Changes",
        isLogoutButton = false,
        modifier = Modifier.padding(vertical = 45.dp),
        click = { })
}
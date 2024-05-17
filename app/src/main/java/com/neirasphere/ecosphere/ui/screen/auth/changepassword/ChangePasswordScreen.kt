package com.neirasphere.ecosphere.ui.screen.auth.changepassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.components.ButtonProfile
import com.neirasphere.ecosphere.ui.components.EditProfileForm
import com.neirasphere.ecosphere.ui.components.SectionTextColumn
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun ChangePasswordScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChangePasswordContent()
    }
}

@Composable
fun ChangePasswordContent(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.change_pass_header_title),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        color = BlackColor,
        modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 30.dp)
    )
    Spacer(modifier = Modifier.height(20.dp))
    SectionTextColumn(title = R.string.edit_sec_title_4) {
        EditProfileForm(label = "Masukan Password", value = "", onValueChange = { })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumn(title = R.string.edit_sec_title_6) {
        EditProfileForm(label = "Masukan Password", value = "", onValueChange = { })
    }
    ButtonProfile(
        label = "Simpan",
        isLogoutButton = false,
        modifier = Modifier.padding(vertical = 35.dp),
        click = { })
}
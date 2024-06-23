package com.neirasphere.ecosphere.presentation.screen.auth.changepassword

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
import com.neirasphere.ecosphere.presentation.components.ButtonProfile
import com.neirasphere.ecosphere.presentation.components.EditPasswordProfileForm
import com.neirasphere.ecosphere.presentation.components.EditProfileForm
import com.neirasphere.ecosphere.presentation.components.SectionTextColumn
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.utils.ActionKeyboard

@Composable
fun ChangePasswordScreen(
    moveToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChangePasswordContent(
            onChangePasswordClick = {}
        )
    }
}

@Composable
fun ChangePasswordContent(
    onChangePasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        EditPasswordProfileForm(
            label = "Masukan Password",
            value = "",
            keyboardAction = ActionKeyboard.NEXT,
            onValueChange = { })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumn(title = R.string.edit_sec_title_6) {
        EditPasswordProfileForm(
            label = "Masukan Password",
            value = "",
            keyboardAction = ActionKeyboard.END,
            onValueChange = { })
    }
    ButtonProfile(
        label = "Simpan",
        isLogoutButton = false,
        modifier = Modifier.padding(vertical = 35.dp),
        click = onChangePasswordClick
    )
}
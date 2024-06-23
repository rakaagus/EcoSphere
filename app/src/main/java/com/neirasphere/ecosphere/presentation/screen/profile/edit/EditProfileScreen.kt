package com.neirasphere.ecosphere.presentation.screen.profile.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.ButtonProfile
import com.neirasphere.ecosphere.presentation.components.EditPasswordProfileForm
import com.neirasphere.ecosphere.presentation.components.EditProfileForm
import com.neirasphere.ecosphere.presentation.components.HeaderEditProfile
import com.neirasphere.ecosphere.presentation.components.SectionTextColumnProfile
import com.neirasphere.ecosphere.utils.ActionKeyboard
import com.neirasphere.ecosphere.utils.TypeKeyboard

@Composable
fun EditProfileScreen(
    moveToProfile: () -> Unit,
    viewModel: EditProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var birtDate by remember {
        mutableStateOf("")
    }


    var location by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        EditProfileContent(
            firstName = firstName,
            lastName = lastName,
            email = email,
            date = birtDate,
            location = location,
            onFirstNameChange = { firstName = it },
            onLastNameChange = { lastName = it },
            onEmailChange = { email = it },
            onDateChange = { birtDate = it },
            onLocationChange = {
                location = it
            },
            onButtonSaveClick = {}
        )
    }
}

@Composable
fun EditProfileContent(
    firstName: String,
    lastName: String,
    email: String,
    date: String,
    location: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onButtonSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    HeaderEditProfile(
        image = R.drawable.example_image_user,
    )
    Spacer(modifier = Modifier.height(20.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_7) {
        EditProfileForm(
            label = stringResource(id = R.string.title_last_name),
            value = firstName,
            keyboardType = TypeKeyboard.NAME,
            keyboardAction = ActionKeyboard.NEXT,
            onValueChange = {
                onFirstNameChange(it)
            })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_1) {
        EditProfileForm(
            label = stringResource(id = R.string.title_first_name),
            value = lastName,
            keyboardType = TypeKeyboard.NAME,
            keyboardAction = ActionKeyboard.NEXT,
            onValueChange = {
                onLastNameChange(it)
            })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_2) {
        EditProfileForm(
            label = stringResource(id = R.string.title_email),
            value = email,
            keyboardType = TypeKeyboard.EMAIL,
            keyboardAction = ActionKeyboard.NEXT,
            onValueChange = {
                onEmailChange(it)
            })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_3) {
        EditProfileForm(
            label = stringResource(id = R.string.form_edit_date),
            value = date,
            keyboardType = TypeKeyboard.NAME,
            keyboardAction = ActionKeyboard.NEXT,
            onValueChange = {
                onDateChange(it)
            })
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.edit_sec_title_5) {
        EditProfileForm(
            label = stringResource(id = R.string.form_edit_loc),
            value = location,
            keyboardType = TypeKeyboard.NAME,
            keyboardAction = ActionKeyboard.NEXT,
            onValueChange = {
                onLocationChange(it)
            })
    }
    ButtonProfile(
        label = "Save Changes",
        isLogoutButton = false,
        modifier = Modifier.padding(vertical = 45.dp),
        click = onButtonSaveClick
    )
}
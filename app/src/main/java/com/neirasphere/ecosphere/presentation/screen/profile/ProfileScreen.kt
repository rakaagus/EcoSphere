package com.neirasphere.ecosphere.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.ButtonProfile
import com.neirasphere.ecosphere.presentation.components.HeaderProfile
import com.neirasphere.ecosphere.presentation.components.SectionProfile
import com.neirasphere.ecosphere.presentation.components.SectionTextColumnProfile
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        ProfileContent(navController = navController)
    }
}

@Composable
fun ProfileContent(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    HeaderProfile(
        image = R.drawable.example_image_user,
        name = "Erlin",
        location = "Piyungan, Yogyakarta"
    )
    SectionTextColumnProfile(title = R.string.header_title_1) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(NeutralColorGrey),
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionProfile(
                title = R.string.profile_title,
                icon = R.drawable.accoun_user,
                navigateTo = {
                    navController.navigate(Screen.EditProfileScreen.route)
                })
            SectionProfile(
                title = R.string.item_header_1,
                icon = R.drawable.icon_security,
                navigateTo = { })
            SectionProfile(
                title = R.string.item_header_2,
                icon = R.drawable.icon_notification_profile,
                navigateTo = { })
            SectionProfile(
                title = R.string.item_header_3,
                icon = R.drawable.icon_privacy,
                navigateTo = { })
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.header_title_2) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(NeutralColorGrey),
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionProfile(
                title = R.string.item_header_4,
                icon = R.drawable.icon_help,
                navigateTo = { })
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
    SectionTextColumnProfile(title = R.string.header_title_3) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(NeutralColorGrey),
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionProfile(
                title = R.string.item_header_5,
                icon = R.drawable.icon_waste,
                navigateTo = { })
            SectionProfile(
                title = R.string.item_header_6,
                icon = R.drawable.icon_report,
                navigateTo = { })
            SectionProfile(
                title = R.string.item_header_7,
                icon = R.drawable.icon_logout,
                navigateTo = { })
        }
    }
    ButtonProfile(
        label = "Delete Account",
        modifier = Modifier.padding(vertical = 33.dp),
        isLogoutButton = true,
        click = {

        })
}
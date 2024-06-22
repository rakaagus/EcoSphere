package com.neirasphere.ecosphere.presentation.screen.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Looper
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.domain.model.UserData
import com.neirasphere.ecosphere.presentation.components.ButtonProfile
import com.neirasphere.ecosphere.presentation.components.HeaderProfile
import com.neirasphere.ecosphere.presentation.components.SectionProfile
import com.neirasphere.ecosphere.presentation.components.SectionTextColumnProfile
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import java.util.Locale

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var cityName by remember {
        mutableStateOf("Fetching location...")
    }

    viewModel.getUser()
    val user by viewModel.user.collectAsState()

    val context = LocalContext.current

    RequestLocationPermission(onPermissionGranted = {
        GetUserLocation {
            cityName = getCityName(context, it)
        }
    })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        ProfileContent(
            userData = user.user,
            moveToEditProfile = {
                navController.navigate(Screen.EditProfileScreen.route)
            },
            moveToSecurity = {},
            moveToNotifSetting = {},
            moveToChangePassword = {
                navController.navigate(Screen.ChangePasswordScreen.route)
            },
            moveToHelpScreen = {},
            moveToReport = {},
            onLogoutClick = {},
            clickFreeUp = {},
            locationUser = cityName
        )
    }
}

@Composable
fun ProfileContent(
    userData: UserData?,
    moveToEditProfile: () -> Unit,
    moveToSecurity: () -> Unit,
    moveToNotifSetting: () -> Unit,
    moveToChangePassword: () -> Unit,
    moveToHelpScreen: () -> Unit,
    clickFreeUp: () -> Unit,
    moveToReport: () -> Unit,
    onLogoutClick: () -> Unit,
    locationUser: String,
    modifier: Modifier = Modifier
) {
    HeaderProfile(
        avatarUser = userData?.avatar,
        name = "${userData?.firstName}",
        location = locationUser
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
                    moveToEditProfile()
                })
            SectionProfile(
                title = R.string.item_header_3,
                icon = R.drawable.icon_privacy,
                navigateTo = {
                    moveToChangePassword()
                })
            SectionProfile(
                title = R.string.item_header_1,
                icon = R.drawable.icon_security,
                navigateTo = {
                    moveToSecurity()
                })
            SectionProfile(
                title = R.string.item_header_2,
                icon = R.drawable.icon_notification_profile,
                navigateTo = {
                    moveToNotifSetting()
                })
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
                navigateTo = {
                    moveToHelpScreen()
                })
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
                navigateTo = {
                    clickFreeUp()
                })
            SectionProfile(
                title = R.string.item_header_6,
                icon = R.drawable.icon_report,
                navigateTo = {
                    moveToReport()
                })
        }
    }
    ButtonProfile(
        label = "Log out",
        modifier = Modifier.padding(vertical = 33.dp),
        isLogoutButton = true,
        click = {
            onLogoutClick()
        })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermission(
    onPermissionGranted: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(key1 = Unit) {
        permissionState.launchMultiplePermissionRequest()
    }

    when {
        permissionState.allPermissionsGranted -> {
            onPermissionGranted()
        }

        else -> {

        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun GetUserLocation(
    onLocationReceived: (Location) -> Unit,
) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val locationCallback = rememberUpdatedState(newValue = onLocationReceived)

    DisposableEffect(key1 = Unit) {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationListener = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    locationCallback.value(location)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationListener,
            Looper.getMainLooper()
        )
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationListener)
        }
    }
}

fun getCityName(
    context: Context,
    location: Location
) : String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
    return if (!address.isNullOrEmpty()) {
        address[0].locality ?: "Unknown City"
    } else {
        "Unknown City"
    }
}
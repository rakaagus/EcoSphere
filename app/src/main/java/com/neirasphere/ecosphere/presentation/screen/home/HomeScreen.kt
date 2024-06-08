package com.neirasphere.ecosphere.presentation.screen.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.HomeAppBar
import com.neirasphere.ecosphere.presentation.components.HomeCardClassify
import com.neirasphere.ecosphere.presentation.components.SearchBar
import com.neirasphere.ecosphere.presentation.components.SectionTextColumn
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.HomeCategoriesLearnCard
import com.neirasphere.ecosphere.presentation.navigation.Screen
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var cityName by remember {
        mutableStateOf("Fetching location...")
    }

    val context = LocalContext.current

    RequestLocationPermission(onPermissionGranted = {
        GetUserLocation {
            cityName = getCityName(context, it)
        }
    })

    HomeContent(
        viewModel = viewModel,
        moveToProfile = {
            navController.navigate(Screen.ProfileScreen.route)
        },
        locationUser = cityName
    )
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    locationUser: String,
    moveToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeAppBar(name = "Erlin", location = locationUser, moveToProfile = moveToProfile)
        SearchBar(query = "", onQueryChange = {}, modifier = Modifier.padding(horizontal = 16.dp))
        HomeCardClassify("10", "20", "30")
        SectionTextColumn(title = R.string.section_one, modifier = Modifier.padding(top = 25.dp)) {
            viewModel.categoryLearn.collectAsState(initial = UiState.Loading).value.let { state ->
                when (state) {
                    is UiState.Loading -> {
                        viewModel.getLearnHome()
                    }

                    is UiState.Error -> {}
                    is UiState.Success -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.height(450.dp)
                        ) {
                            items(state.data) {
                                HomeCategoriesLearnCard(categoriesLearn = it)
                            }
                        }
                    }
                }
            }
        }
        SectionTextColumn(
            title = R.string.section_two,
            modifier = Modifier.padding(top = 25.dp, bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.map_image),
                contentDescription = "Map",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
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
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
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


@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController())
}
package com.neirasphere.ecosphere.presentation.screen.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Looper
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.domain.model.UserData
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.HomeAppBar
import com.neirasphere.ecosphere.presentation.components.HomeCardClassify
import com.neirasphere.ecosphere.presentation.components.HomeCategoriesLearnCard
import com.neirasphere.ecosphere.presentation.components.SearchBar
import com.neirasphere.ecosphere.presentation.components.SectionTextColumn
import com.neirasphere.ecosphere.presentation.components.SectionTextColumnMap
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.classification.ClassifyHistoryViewModel
import java.util.Locale

@Composable
fun HomeScreen(
    moveToProfile: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    classifyViewModel: ClassifyHistoryViewModel = hiltViewModel(),
    moveToClassificationHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    var cityName by remember {
        mutableStateOf("Fetching location...")
    }

    val yogyakartaLatlng = LatLng(-7.788451947965932, 110.36505903685487)
    val context = LocalContext.current

    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation ?: yogyakartaLatlng, 6f)
    }

    viewModel.dataUser()
    val user by viewModel.user.collectAsState()

    RequestLocationPermission(
        onPermissionGranted = {
            GetUserLocation {
                userLocation = LatLng(it.latitude, it.longitude)
            }
        })

    RequestLocationPermission(onPermissionGranted = {
        GetUserLocation {
            cityName = getCityName(context, it)
        }
    })

    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.TERRAIN))
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }

    LaunchedEffect(userLocation) {
        userLocation?.let {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(userLocation ?: yogyakartaLatlng, 12f, 0f, 0f)
                ),
                durationMs = 1000
            )
        }
    }

    HomeContent(
        viewModel = viewModel,
        classifyViewModel = classifyViewModel,
        moveToProfile = moveToProfile,
        user = user.dataUser,
        cityNameUser = cityName,
        locationUser = userLocation,
        cameraState = cameraPositionState,
        mapStyle = properties,
        mapSetting = uiSettings,
        moveToClassificationHistory = moveToClassificationHistory,
    )
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    classifyViewModel: ClassifyHistoryViewModel,
    user: UserData?,
    cityNameUser: String,
    locationUser: LatLng?,
    cameraState: CameraPositionState,
    mapStyle: MapProperties,
    mapSetting: MapUiSettings,
    moveToProfile: () -> Unit,
    moveToClassificationHistory: () -> Unit,
    modifier: Modifier = Modifier
) {

    val userName = if (user != null) "${user?.firstName}" else "Unknows"

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val organicCount by classifyViewModel.organicCount.collectAsState()
        val anOrganicCount by classifyViewModel.anOrganicCount.collectAsState()
        val totalCount by classifyViewModel.totalCount.collectAsState()

        HomeAppBar(
            name = userName,
            location = cityNameUser,
            userImage = user?.avatar,
            moveToProfile = moveToProfile
        )
        SearchBar(query = "", onQueryChange = {}, modifier = Modifier.padding(horizontal = 16.dp))
        HomeCardClassify(
            count = totalCount,
            inorganic = anOrganicCount,
            organic = organicCount,
            moveToClassificationHistory = moveToClassificationHistory,
        )
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
        SectionTextColumnMap(
            title = "TPS Sekitar $cityNameUser",
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        ) {
            GoogleMap(
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(MaterialTheme.shapes.small),
                cameraPositionState = cameraState,
                properties = mapStyle,
                uiSettings = mapSetting
            ) {
                locationUser?.let {
                    Marker(
                        state = MarkerState(it),
                        title = "Your Location",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
                }
            }
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
): String {
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
    HomeScreen(moveToProfile = {}, moveToClassificationHistory = {})
}
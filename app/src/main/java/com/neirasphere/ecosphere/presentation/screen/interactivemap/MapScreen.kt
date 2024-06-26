package com.neirasphere.ecosphere.presentation.screen.interactivemap

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.SearchMapComponent
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun MapScreen(
    clickToDetailTps: (Long) -> Unit,
    viewModel: MapViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val yogyakartaLatlng = LatLng(-7.788451947965932, 110.36505903685487)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(yogyakartaLatlng, 6f)
    }

    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    RequestLocationPermission(onPermissionGranted = {
        GetUserLocation {
            userLocation = LatLng(it.latitude, it.longitude)
        }
    })

    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.TERRAIN))
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }


    viewModel.tpsUiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllTpsData()
            }
            is UiState.Error -> {}
            is UiState.Success -> {

                LaunchedEffect(uiState.data, userLocation) {
                    if(userLocation == null){
                        val boundsBuilder = LatLngBounds.Builder()
                        uiState.data.forEach { boundsBuilder.include(it.latLong) }
                        val bounds = boundsBuilder.build()
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newLatLngBounds(bounds, 50)
                        )
                    }else {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(userLocation!!, 12f, 0f, 0f)
                            ),
                            durationMs = 1000
                        )
                    }
                }

                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    MapContent(
                        clickToDetailTps = clickToDetailTps,
                        cameraState = cameraPositionState,
                        mapStyle = properties,
                        mapSetting = uiSettings,
                        mapData = uiState.data,
                        userLocation = userLocation
                    )
                }
            }
        }
    }
}

@Composable
fun MapContent(
    clickToDetailTps: (Long) -> Unit,
    mapData: List<com.neirasphere.ecosphere.domain.model.MapData>,
    cameraState: CameraPositionState,
    mapStyle: MapProperties,
    mapSetting: MapUiSettings,
    userLocation: LatLng?,
    modifier: Modifier = Modifier
) {
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraState,
        properties = mapStyle,
        uiSettings = mapSetting
    ) {
        mapData.forEach {
            Marker(
                state = MarkerState(it.latLong),
                title = it.title
            )
        }
        userLocation?.let {
            Marker(
                state = MarkerState(it),
                title = "Your Location",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
        }
    }
    Column {
        MapSearchBar(navController = rememberNavController())
        Spacer(modifier = Modifier.weight(1f))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(mapData) {
                TpsCard(it, clickToDetailTps = clickToDetailTps)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapSearchBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    var dataSearch = remember {
        mutableStateListOf(
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
        )
    }

    SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            dataSearch.add(
                com.neirasphere.ecosphere.domain.model.SearchModel(
                    text,
                    text
                )
            )
            active = false
        },
        active = active,
        onActiveChange = { active = it },
        leadingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            } else {
                Icon(
                    modifier = Modifier.clickable { navController.navigateUp() },
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            }
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = NeutralColorWhite
        ),
        placeholder = {
            Text(
                text = "TPS Terdekat",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF969696)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = if (!active) 16.dp else 0.dp)
            .clip(if (!active) RoundedCornerShape(36.dp) else RoundedCornerShape(0.dp))
    ) {
        LazyColumn {
            item {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "User Current Location")
                }
                Divider()
            }
            items(dataSearch) {
                SearchMapComponent(it.title, it.desc)
            }
        }
    }
}

@Composable
fun TpsCard(
    data: com.neirasphere.ecosphere.domain.model.MapData,
    clickToDetailTps: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(NeutralColorWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .size(width = 227.dp, height = 191.dp)
            .clickable {
                clickToDetailTps(data.id)
            }
    ) {
        Column(
        ) {
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = data.image[0]),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.offset(
                        x = -20.dp,
                        y = 10.dp
                    )
                )
            }
            Text(
                text = data.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = data.detailLocation,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF969696),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = data.rating.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "",
                    tint = Color(0xFFFFB015),
                    modifier = Modifier.size(14.dp)
                )
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

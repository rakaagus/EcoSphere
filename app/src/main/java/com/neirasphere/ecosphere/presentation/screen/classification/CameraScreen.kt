package com.neirasphere.ecosphere.presentation.screen.classification

import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.rounded.FlashOff
import androidx.compose.material.icons.rounded.FlashOn
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.classification.component.CameraPreview
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.utils.executor
import com.neirasphere.ecosphere.utils.getCameraProvider
import com.neirasphere.ecosphere.utils.takePicture
import com.neirasphere.ecosphere.utils.toFile
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun CameraScreen(
    moveToResult: (file: File) -> Unit,
    navHostController: NavHostController,
    viewModel: ClassificationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    CameraContent(
        onImageFiled = { file ->
            moveToResult(file)
        },
        onBackClick = {
            navHostController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.CameraScreen.route) {
                    inclusive = true
                }
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraContent(
    onBackClick: () -> Unit,
    onImageFiled: (File) -> Unit,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val permissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                coroutineScope.launch {
                    onImageFiled(uri.toFile(context))
                }
            }
        }
    )


    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (permissionState.status.isGranted) {
            val lifecycleOwner = LocalLifecycleOwner.current
            var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
            var flashModeOn by remember { mutableStateOf(false) }
            val imageCaptureUseCase by remember {
                mutableStateOf(
                    ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build()
                )
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .consumeWindowInsets(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
            )
            {
                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onUseCase = { useCase ->
                        previewUseCase = useCase
                    }
                )
                IconButton(
                    onClick = {
                        onBackClick()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = PrimaryColor
                    ),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 10.dp, start = 16.dp)
                        .size(34.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.icon_history_classify),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 16.dp)
                        .size(34.dp)
                        .clickable {

                        }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0x51000000)
                    ),
                    modifier = Modifier
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Image,
                        tint = Color.White,
                        contentDescription = "Pick Image from Gallery"
                    )
                }
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                val file = imageCaptureUseCase.takePicture(context.executor)
                                onImageFiled(file)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    },
                    modifier = Modifier
                        .size(80.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Circle,
                        tint = PrimaryColor,
                        contentDescription = "Scan",
                        modifier = Modifier
                            .size(80.dp)
                    )
                }
                IconButton(
                    onClick = {
                        flashModeOn = !flashModeOn
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0x51000000)
                    ),
                    modifier = Modifier
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = if (flashModeOn)
                            Icons.Rounded.FlashOn else Icons.Rounded.FlashOff,
                        tint = Color.White,
                        contentDescription = "Turn on The Flash"
                    )
                }
            }
            LaunchedEffect(previewUseCase, flashModeOn) {
                val cameraProvider = context.getCameraProvider()
                try {
                    cameraProvider.unbindAll()
                    val camera = cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        previewUseCase,
                        imageCaptureUseCase
                    )
                    if (camera.cameraInfo.hasFlashUnit()) {
                        camera.cameraControl.enableTorch(flashModeOn)
                    }
                } catch (e: Exception) {
                    Log.e("Camera Content", "ScanContent: $e")
                }
            }
        } else {
//            permissionState.launchPermissionRequest()
        }
    }
}
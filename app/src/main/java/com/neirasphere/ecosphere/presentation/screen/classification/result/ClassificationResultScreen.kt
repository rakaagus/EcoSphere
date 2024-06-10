package com.neirasphere.ecosphere.presentation.screen.classification.result

import com.neirasphere.ecosphere.presentation.screen.classification.ClassificationViewModel

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ClassificationResultScreen(
    viewModel: ClassificationResultViewModel = hiltViewModel(),
    navController : NavHostController,
) {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val result by viewModel.result.observeAsState()
    var classificationResponse by remember { mutableStateOf<ClassifyResult?>(null) }
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                imageUri.value?.let { uri ->
                    val file = uriToFile(uri, context)
                    viewModel.classifyTrash(file)
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }

    fun retakePhoto() {

        imageUri.value = null
        classificationResponse = null
        isBottomSheetVisible = false
        if (cameraPermissionState.status.isGranted) {
            val uri = createImageUri(context)
            imageUri.value = uri
            cameraLauncher.launch(uri)
        } else {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Letakkan barang Anda dalam bingkai pemindai",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painterResource(id = R.drawable.camera_frame),
            contentDescription = null,
            modifier = Modifier
                .size(320.dp, 450.dp)
                .padding(top = 22.dp, bottom = 30.dp)
        )

        Button(
            onClick = {
                val uri = createImageUri(context)
                imageUri.value = uri
                cameraLauncher.launch(uri)
            },
//            enabled = !isBottomSheetVisible,
            modifier = Modifier
                .size(80.dp)
                .background(Color.White),
            content = {
                Icon(
                    imageVector = Icons.Default.Camera,
                    contentDescription = "Capture Image",
                    tint = PrimaryColor,
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (result) {
            is Result.Loading -> {
                CircularProgressIndicator()
            }
            is Result.Success -> {
                classificationResponse = (result as Result.Success<ClassifyResult>).data
                isBottomSheetVisible = true
            }
            is Result.Error -> {
                Text("Upload Failed: ${(result as Result.Error).message}")
            }
            else -> { Text("Upload a file to see the result") }
        }
    }

    LaunchedEffect(isBottomSheetVisible) {
        Log.d("ClassificationScreen", "isBottomSheetVisible: $isBottomSheetVisible")
    }

    if (isBottomSheetVisible) {
        BottomSheet(
            onDismiss = {
                Log.d("BottomSheet", "Dismiss called")
                isBottomSheetVisible = false
            },
            navController = navController,
            imageUri = imageUri,
            classificationResponse = classificationResponse,
            onRetakePhoto = {
                isBottomSheetVisible = false
                retakePhoto() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    imageUri: MutableState<Uri?>,
    navController: NavHostController,
    classificationResponse: ClassifyResult?,
    onRetakePhoto: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(modalBottomSheetState.isVisible) {
        if (!modalBottomSheetState.isVisible) {
            onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        BottomSheetContent(
            navController = navController,
            imageUri = imageUri,
            classificationResponse = classificationResponse,
            onRetakePhoto = onRetakePhoto,
            onDismiss = onDismiss,
        )
    }
}


@Composable
fun BottomSheetContent(
    navController: NavHostController,
    imageUri : MutableState<Uri?>,
    classificationResponse: ClassifyResult?,
    onRetakePhoto: () -> Unit,
    onDismiss: () -> Unit,
){
    Column(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            .height(500.dp)
    ) {
        Text(
            "Sampah Terdeteksi",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)
        )
        Row(){
            Image(
                painter = rememberAsyncImagePainter(imageUri.value),
                contentDescription = null,
                modifier = Modifier.size(84.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp)
            ){
                classificationResponse?.let {
                    Text(it.classCategory ?: "NA",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
//                Text("Sampah Anorganik",
//                    style = MaterialTheme.typography.labelSmall,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//                Text("Botol Plastik",
//                    style = MaterialTheme.typography.bodySmall,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
            }
            Spacer(modifier = Modifier
                .weight(1f)
            )
            //tambah logic utk retake photo
            Image(painterResource(id = R.drawable.icon_refresh),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        Log.d("BottomSheet", "Image clicked")
                        onDismiss()
                        onRetakePhoto()
                    }
            )
        }
        Text(text = "Deskripsi:",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )
        classificationResponse?.let {
            Text("Description: ${it.description ?: ""}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }
//        Text(text = "Sampah ini termasuk anorganik dan sering ditemui dalam lingkungan sehari-hari. Botol plastik merupakan wadah yang terbuat dari bahan sintetis seperti PET (Polyethylene Terephthalate) atau HDPE (High-Density Polyethylene), digunakan untuk berbagai macam minuman dan produk konsumen lainnya.",
//            style = MaterialTheme.typography.bodySmall,
//            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
//        )
        Text(
            "Wow! Sampah ini bisa di daur ulang loh✨",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )
        ButtonAuth(
            label = "Yuk, daur ulang di sini!",
            click = { navController.navigate(Screen.RecycleScreen.route) })

    }
}

fun createImageUri(context: Context): Uri {
    val contentResolver = context.contentResolver
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val fileName = "JPEG_$timeStamp.jpg"

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/")
    }

    return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
}

fun uriToFile(uri: Uri, context: Context): File {
    val contentResolver = context.contentResolver
    val tempFile = File.createTempFile("temp_image", ".jpg", context.cacheDir)
    tempFile.outputStream().use { outputStream ->
        contentResolver.openInputStream(uri)?.copyTo(outputStream)
    }
    return tempFile
}
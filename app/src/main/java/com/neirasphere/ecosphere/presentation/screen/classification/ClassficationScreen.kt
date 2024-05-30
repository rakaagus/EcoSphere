package com.neirasphere.ecosphere.presentation.screen.classification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClassificationScreen(
    navController: NavHostController,
) {

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "${context.packageName}.provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                val permissionCheckResult = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                )
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            modifier = Modifier
                .size(80.dp)
                .background(Color.White), // Mengatur warna latar belakang tombol
            content = {
                Icon(
                    imageVector = Icons.Default.Camera,
                    contentDescription = "Capture Image",
                    tint = PrimaryColor,
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
    }

    if (capturedImageUri.path?.isNotEmpty() == true) {
        var isBottomSheetVisible by remember { mutableStateOf(false) }
        Column {
            Image(
                modifier = Modifier
                    .padding(16.dp, 8.dp),
                painter = rememberImagePainter(capturedImageUri),
                contentDescription = null
            )
            BottomSheet(
                onDismiss = { isBottomSheetVisible = false },
                navController = navController
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    navController: NavHostController,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        BottomSheetContent(navController = navController)
    }
}


@Composable
fun BottomSheetContent(
    navController: NavHostController,
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
            Image(painterResource(id = R.drawable.paper_3),
                contentDescription = null,
                modifier = Modifier.size(84.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp)
            ){
                Text("Sampah Anorganik",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text("Botol Plastik",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier
                .weight(1f)
            )
            Image(painterResource(id = R.drawable.icon_refresh),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Text(text = "Deskripsi:",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )
        Text(text = "Sampah ini termasuk anorganik dan sering ditemui dalam lingkungan sehari-hari. Botol plastik merupakan wadah yang terbuat dari bahan sintetis seperti PET (Polyethylene Terephthalate) atau HDPE (High-Density Polyethylene), digunakan untuk berbagai macam minuman dan produk konsumen lainnya.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
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

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, //prefix
        ".jpg", //suffix
        externalCacheDir //directory
    )
    return image
}

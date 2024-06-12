package com.neirasphere.ecosphere.presentation.screen.classification


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.classification.component.ShimmerImage
import com.neirasphere.ecosphere.utils.shimmerEffect
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun ClassificationScreen(
    navController: NavHostController,
    file: File?,
    viewModel: ClassificationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var shimmerLoading by remember { mutableStateOf(true) }
    val success = viewModel.state.collectAsState().value.success
    val error = viewModel.state.collectAsState().value.error
    var classificationResponse by remember { mutableStateOf<ClassifyResult?>(null) }

    LaunchedEffect(key1 = Unit) {
        delay(100)
        if (file != null) {
            viewModel.classifyTrash(file)
        }
    }

    if (success) {
        viewModel.state.collectAsState().value.result.let {
            classificationResponse = it
        }
        isBottomSheetVisible = true
        shimmerLoading = false
    }

    if (error != null) {
        viewModel.state.collectAsState().value.error.let {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        ShimmerImage(isLoading = shimmerLoading) {
            AsyncImage(
                model = file,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        }
    }
    if (isBottomSheetVisible) {
        BottomSheet(
            onDismiss = { isBottomSheetVisible = false },
            file = file,
            navController = navController,
            classificationResponse = classificationResponse
        ) {
            isBottomSheetVisible = false
            navController.navigate(Screen.CameraScreen.route) {
                popUpTo(Screen.ClassifyScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    file: File?,
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
            file = file,
            classificationResponse = classificationResponse,
            onRetakePhoto = onRetakePhoto,
            onDismiss = onDismiss,
        )
    }
}


@Composable
fun BottomSheetContent(
    navController: NavHostController,
    file: File?,
    classificationResponse: ClassifyResult?,
    onRetakePhoto: () -> Unit,
    onDismiss: () -> Unit,
) {
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
        Row() {
            AsyncImage(
                model = file, contentDescription = null,
                modifier = Modifier.size(84.dp).clip(MaterialTheme.shapes.extraSmall),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp)
            ) {
                classificationResponse?.let {
                    Text(
                        it.classCategory ?: "NA",
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
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            //tambah logic utk retake photo
            Image(
                painterResource(id = R.drawable.icon_refresh),
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
        Text(
            text = "Deskripsi:",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )
        classificationResponse?.let {
            Text(
                "Description: ${it.description ?: ""}",
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
            click = {
                navController.navigate(Screen.RecycleScreen.route) {
                    popUpTo(Screen.ClassifyScreen.route) {
                        inclusive = true
                    }
                }
            })

    }
}

package com.neirasphere.ecosphere.presentation.screen.classification

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.classification.component.ShimmerImage
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun ClassificationScreen(
    navController: NavHostController,
    file: File?,
    viewModel: ClassificationViewModel = hiltViewModel(),
    historyViewModel: ClassifyHistoryViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var shimmerLoading by remember { mutableStateOf(true) }
    var classificationResponse by remember { mutableStateOf<ClassifyResult?>(null) }

    val state = viewModel.state.collectAsState().value
    val success = state.success
    val error = state.error
    val result = state.result

    LaunchedEffect(file, result) {
        delay(100)
        if (file != null && result == null) {
            viewModel.classifyTrash(file)
        }
    }

    LaunchedEffect(success) {
        if (success && result != null) {
            classificationResponse = result
            val classifyHistory = ClassifyHistory(
                title = result.classCategory ?: "NONE CLASSIFY",
                image = file?.absolutePath ?: "" ,
            )
            Log.d("ClassificationScreen", "File: ${file?.absolutePath ?: "null"}")
            historyViewModel.insertHistory(classifyHistory)
            isBottomSheetVisible = true
            shimmerLoading = false
        }
    }
    Log.d("ClassificationScreen", "Outside: $isBottomSheetVisible, classificationResponse: $classificationResponse")

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
        Log.e("bottom","show bottomsheet")
        BottomSheet(
            onDismiss = {},
            file = file,
            navController = navController,
            classificationResponse = classificationResponse
        ) {
            isBottomSheetVisible = false
            classificationResponse = null
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
                modifier = Modifier
                    .size(84.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp)
            ) {
                classificationResponse?.let {
                    Text(text = "Jenis Sampah:",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = it.classCategory ?: "NA",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            //retake photo icon
            Image(
                painterResource(id = R.drawable.icon_refresh),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        Log.d("BottomSheet", "Retake image clicked")
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
                text = it.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }
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

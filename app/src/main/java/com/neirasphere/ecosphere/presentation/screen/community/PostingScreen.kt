package com.neirasphere.ecosphere.presentation.screen.community

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.auth.login.LoadingDialog
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.utils.toFile

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CommunityViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val postLoading = viewModel.postState.collectAsState().value.isLoading
    val postSuccess = viewModel.postState.collectAsState().value.isSuccess
    var isLoadingDialogShow by remember {
        mutableStateOf(false)
    }
    var isSuccessDialogShow by remember {
        mutableStateOf(false)
    }
    
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        imageUri = uri
    }

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_MEDIA_IMAGES
    } else android.Manifest.permission.READ_EXTERNAL_STORAGE

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        isGranted: Boolean ->
        if (isGranted) {}
    }

    if (postLoading) {
        LoadingDialog(onDismissRequest = { isLoadingDialogShow = false })
    }

    if (postSuccess) {
        DialogPostSuccess(
            onDismissRequest = { isSuccessDialogShow = false },
            moveToPosts = {
                navController.navigate(Screen.CommunityScreen.route) {
                    popUpTo(Screen.PostingScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }

    Scaffold(
        bottomBar = {
            Column {
                Row(
                    modifier = modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Semua orang dapat membalas",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Divider()
                Row(
                    modifier = modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = "Photo",
                        modifier = modifier
                            .clickable {
                                val isGranted = ContextCompat.checkSelfPermission(
                                    context, permissionToRequest
                                ) == PackageManager.PERMISSION_GRANTED

                                if (isGranted) {
                                    launcher.launch("image/*")
                                } else {
                                    permissionLauncher.launch(permissionToRequest)
                                }
                            }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(imageVector = Icons.Default.GraphicEq, contentDescription = "Audio")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(imageVector = Icons.Default.Gif, contentDescription = "GIF")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "List")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Audio")
                }
            }
        }
    ) {
        Column {
            Row(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Batalkan",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .clickable(
                            onClick = {
                                showBottomSheet = true
                            }
                        )
                )
                Button(
                    onClick = {
                        if (imageUri != null) {
                            viewModel.postWithImage(
                                post = text,
                                img = imageUri!!.toFile(context)
                            )
                        } else {
                            viewModel.post(post = text)
                        }
//                        navController.navigate(Screen.DummyDetailPostScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Posting",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = modifier
                        .size(50.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.example_image_user),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Post User Avatar"
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Column {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = "Image Attachment",
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clip(shape = RoundedCornerShape(24.dp))
                        )
                    } else {}
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = NeutralColorWhite,
                            focusedContainerColor = NeutralColorWhite,
                            unfocusedIndicatorColor = Color.White
                        ),
                        placeholder = {
                            Text(text = "Tulis di sini")
                        },
                        shape = RoundedCornerShape(24.dp)
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            modifier = modifier,
            sheetState = sheetState
        ) {
            Column(
                modifier = modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                    Text(
                        text = "Hapus",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.BorderColor,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                    Text(
                        text = "Simpan Draf",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Button(
                    onClick = { navController.navigateUp() },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Text(
                        text = "Batalkan",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun DialogPostSuccess(
    onDismissRequest: () -> Unit,
    moveToPosts: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
               Text(
                   text = "Yey, Kamu berhasil membuat Postingan!",
                   style = MaterialTheme.typography.bodyMedium
               )
        },
        confirmButton = {
            TextButton(onClick = { moveToPosts() }) {
                Text(
                    text = "Kembali ke komunitas",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp
                    ),
                    color = PrimaryColor
                )
            }
        })
}
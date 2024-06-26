package com.neirasphere.ecosphere.presentation.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun HomeAppBar(
    moveToProfile: () -> Unit,
    name: String,
    location: String,
    userImage: String?,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = userImage,
            placeholder = painterResource(id = R.drawable.image_default),
            error = painterResource(id = R.drawable.image_default),
            contentDescription = "UserImage",
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)
                .clickable {
                    moveToProfile()
                }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.hello_user, name),
                color = BlackColor,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 12.sp
                )
            )
            Text(
                text = location,
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.notif_icon),
                contentDescription = "Notif",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CommunityAppBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            CommunitySearchBar(onBackClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.CommunityScreen.route) {
                        inclusive = true
                    }
                }
            })
        }

    }
}

@Composable
fun PostingBottomBar(
    modifier: Modifier = Modifier
) {
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
            Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(imageVector = Icons.Default.GraphicEq, contentDescription = "Audio")
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(imageVector = Icons.Outlined.Photo, contentDescription = "Photo")
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(imageVector = Icons.Default.Gif, contentDescription = "GIF")
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "List")
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Audio")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
    onBackClick: () -> Unit,
    @StringRes title: Int? = null,
    actionIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        title = {
            if (title != null) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ), color = BlackColor
                )
            } else {

            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = PrimaryColor
                ),
                modifier = Modifier.size(34.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        actions = {
            actionIcon()
        }
    )
}

@Composable
fun HeaderProfile(
    avatarUser: String?,
    name: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = avatarUser,
            placeholder = painterResource(id = R.drawable.image_default),
            error = painterResource(R.drawable.image_default),
            contentDescription = "UserImage",
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = name,
            color = BlackColor,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = location,
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun HeaderEditProfile(
    avatarUser: String?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            AsyncImage(
                model = avatarUser,
                placeholder = painterResource(id = R.drawable.image_default),
                error = painterResource(R.drawable.image_default),
                contentDescription = "UserImage",
                modifier =  Modifier
                    .padding(5.dp)
                    .size(120.dp)
                    .clip(CircleShape)
            )
            IconButton(
                onClick = { },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .offset(y = -10.dp, x = -5.dp)
                    .size(30.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Edit",
                    tint = PrimaryColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun HeaderEditProfilePrev() {
    HeaderEditProfile("")
}
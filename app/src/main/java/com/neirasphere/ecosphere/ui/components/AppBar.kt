package com.neirasphere.ecosphere.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun HomeAppBar(
    navController: NavController,
    name: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.example_image_user),
            contentDescription = "UserImage",
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Screen.ProfileScreen.route)
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
            CommunitySearchBar(navController = navController)
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
    navController: NavController,
    @StringRes title: Int? = null,
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
                    navController.navigateUp()
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
        }
    )
}

@Composable
fun HeaderProfile(
    @DrawableRes image: Int,
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
        Image(
            painter = painterResource(id = image),
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
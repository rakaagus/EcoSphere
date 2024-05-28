package com.neirasphere.ecosphere.presentation.screen.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.presentation.components.CenterTopAppBar
import com.neirasphere.ecosphere.presentation.components.PostAvatarAndInfo
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun DetailPostScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    postId: Int?
) {
    val post = DataSource.communityPostData().filter { post ->
        post.id == postId
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CenterTopAppBar(navController = navController, title = R.string.title_detail_post)
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            DetailPostContent(
                post = post,
                navController = navController
            )
        }
    }
}

@Composable
private fun DetailPostContent(
    post: List<com.neirasphere.ecosphere.domain.model.CommunityPost>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PostAvatarAndInfo(post = post[0], navController = navController)
        Column(
            modifier = modifier
            .padding(vertical = 16.dp)
        ) {
            Text(
                text = post[0].text,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp
                )
            )
            if (post[0].image != null) {
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(post[0].image!!),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
        }
        Row {
            Text(
                text = "${post[0].timeDisplay()} · ${post[0].dateDisplay()}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                )
            )
        }
        Divider(
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            modifier = modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${post[0].numberDisplay(post[0].views)} Tayangan",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = "${post[0].numberDisplay(post[0].comments)} Komentar",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = "${post[0].numberDisplay(post[0].likes)} Suka",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                )
            )
        }
        Divider()
        Row(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 48.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.Outlined.Comment, contentDescription = null)
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
            Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = null)
            Icon(imageVector = Icons.Outlined.Upload, contentDescription = null)
        }
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DummyDetailPostScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = {
                Text(
                    text = "Postinganku",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ), color = BlackColor
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.CommunityScreen.route)
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
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = modifier
                            .size(50.dp)
                            .clip(shape = CircleShape),
                        painter = painterResource(id = R.drawable.example_image_user),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Post User Avatar"
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Column {
                        Text(
                            text = "Erlin Marbella",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp
                            )
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(
                            text = "@erlinmarbella32",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp
                            )
                        )
                    }
                }
                Column(
                    modifier = modifier
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "HAII GAISS\n" + "Kalian punya hasil olahan limbah apa nih yang bisa di sharing?",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp
                        )
                    )
                }
                Row {
                    Text(
                        text = "01.34 PM · 21 Mei 2024",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp
                        )
                    )
                }
                Divider(
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp,
                            horizontal = 48.dp
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Outlined.Comment, contentDescription = null)
                    Icon(imageVector = Icons.Outlined.BorderColor, contentDescription = null)
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
                    Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = null)
                    Icon(imageVector = Icons.Outlined.Upload, contentDescription = null)
                }
                Divider()
            }
        }
    }
}
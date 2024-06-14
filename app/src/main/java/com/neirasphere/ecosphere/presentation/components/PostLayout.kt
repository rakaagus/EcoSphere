package com.neirasphere.ecosphere.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PostLayout(
    post: com.neirasphere.ecosphere.domain.model.CommunityPost,
    navController: NavController,
    onItemClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                    .clickable {
                onItemClicked(post.id)
            }
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = post.user.avatar),
                contentScale = ContentScale.Crop,
                contentDescription = "Post User Avatar"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = post.user.nama,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 12.sp
                        )
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "@${post.user.username} · ${post.timeAgo()}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp
                        )
                    )
                }
                PostAndImage(post = post)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        PostActions(post = post)
                    }
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Outlined.Flag, contentDescription = null, Modifier.size(16.dp))
                            Spacer(modifier = Modifier.padding(4.dp))
                            Icon(imageVector = Icons.Outlined.BookmarkBorder, contentDescription = null, Modifier.size(16.dp))
                            Spacer(modifier = Modifier.padding(4.dp))
                            Icon(imageVector = Icons.Outlined.Upload, contentDescription = null, Modifier.size(16.dp))
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun PostAvatarAndInfo(
    post: com.neirasphere.ecosphere.domain.model.CommunityPost,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .size(50.dp)
                .clip(shape = CircleShape),
            painter = painterResource(id = post.user.avatar),
            contentScale = ContentScale.Crop,
            contentDescription = "Post User Avatar"
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column {
            Text(
                text = post.user.nama,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "@${post.user.username}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun PostAndImage(post: com.neirasphere.ecosphere.domain.model.CommunityPost, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = post.text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp
            )
        )
        if (post.image != null) {
            Spacer(modifier = Modifier.size(10.dp))
            Image(
                painter = painterResource(post.image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun PostActions(post: com.neirasphere.ecosphere.domain.model.CommunityPost, modifier: Modifier = Modifier) {
    val isLiked = remember { mutableStateOf(false) }

    fun toggleLike() {
        isLiked.value = !isLiked.value
    }

    Row(
        modifier = modifier
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        PostActionWithText(Icons.Default.Comment, post.numberDisplay(post.comments), {})
        PostActionWithText(
            if (isLiked.value) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.Favorite
            },
            post.numberDisplay(post.likes),
            { toggleLike() }
        )
        PostActionWithText(Icons.Default.BarChart, post.numberDisplay(post.views), {})
    }
}

@Composable
fun PostActionWithText(icon: ImageVector, content: String, onClick: () -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp),
                tint = if (icon == Icons.Filled.Favorite) Color.Red else Color.Black
            )
        }
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 12.sp)
        )
    }
}

@Composable
fun PostAction(icon: ImageVector) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
    }
}

@Composable
fun PostOptions(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.OutlinedFlag,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp)
        )
        Icon(
            imageVector = Icons.Default.BookmarkBorder,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp)
        )
        Icon(
            imageVector = Icons.Default.ArrowUpward,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp)
        )
    }
}
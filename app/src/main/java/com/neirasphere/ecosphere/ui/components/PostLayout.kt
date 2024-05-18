package com.neirasphere.ecosphere.ui.components

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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.model.CommunityPost

@Composable
fun PostLayout(
    post: CommunityPost,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
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
                Text(
                    text = post.user.nama,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "@${post.user.username} · ${post.timeAgo()}",
                    style = TextStyle(fontSize = 12.sp)
                )
                PostAndImage(post = post)
                PostActions(post = post)
            }

        }
    }
}

@Composable
fun PostAvatarAndInfo(
    post: CommunityPost,
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
        Text(
            text = post.user.nama,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "@${post.user.username} · ${post.timeAgo()}",
            style = TextStyle(fontSize = 12.sp)
        )
    }
}

@Composable
private fun PostAndImage(post: CommunityPost, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(top = 8.dp)) {
        Text(
            text = post.text,
            style = TextStyle(fontSize = 14.sp)
        )
        if (post.image != null) {
            Spacer(modifier = Modifier.size(10.dp))
            Image(
                painter = painterResource(post.image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun PostActions(post: CommunityPost, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PostActionWithText(Icons.Default.Comment, post.comments.toString())
        PostActionWithText(
            if (post.liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder, post.likes.toString()
        )
        PostAction(Icons.Default.Share)
    }
}

@Composable
fun PostActionWithText(icon: ImageVector, content: String) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.size(16.dp),
            tint = if (icon == Icons.Default.Favorite) Color.Black else Color.Red
            )
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = content, fontSize = 12.sp)
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
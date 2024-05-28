package com.neirasphere.ecosphere.presentation.screen.community

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.presentation.components.MagicTabItem
import com.neirasphere.ecosphere.presentation.components.MagicTabLayout
import com.neirasphere.ecosphere.presentation.components.PostLayout
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CommunityScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        MagicTabItem(
            title = "Populer"
        ) {
          TabItem(
              item = DataSource.communityPostData().sortedByDescending { it.likes },
              navController = navController
          )
        },
        MagicTabItem(
            title = "Terbaru"
        ) {
            TabItem(
                item = DataSource.communityPostData().sortedBy { it.timeDiff() },
                navController = navController
            )
        }
    )
    MagicTabLayout(
        modifier = Modifier.layoutId("tablayout"),
        tabList = tabs,
        tabIndicatorColor = PrimaryColor
    )
}

@Composable
fun TabItem(
    item: List<com.neirasphere.ecosphere.domain.model.CommunityPost> = DataSource.communityPostData(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(bottom = 0.dp)
    ) {
        items(item, key = { it.id }) {
            PostLayout(post = it, navController = navController) { postId ->
                navController.navigate(Screen.DetailPostScreen.route + "/${postId}")
            }
            Divider()
        }
    }
}

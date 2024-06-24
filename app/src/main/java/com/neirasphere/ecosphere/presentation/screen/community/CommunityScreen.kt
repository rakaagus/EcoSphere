package com.neirasphere.ecosphere.presentation.screen.community

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.domain.model.CommunityPostSQL
import com.neirasphere.ecosphere.presentation.components.MagicTabItem
import com.neirasphere.ecosphere.presentation.components.MagicTabLayout
import com.neirasphere.ecosphere.presentation.components.PostLayout
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.auth.login.LoadingDialog
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CommunityScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = hiltViewModel()
) {
    val state by viewModel.getPostsState.collectAsStateWithLifecycle()
    val loading = state.isLoading
    var isLoadingDialogShow by remember { mutableStateOf(false) }
    Log.d("cek posts", "${state.posts}")

    if (loading) {
        LoadingDialog(onDismissRequest = { isLoadingDialogShow = false })
    }

    val tabs = listOf(
        MagicTabItem(
            title = "Populer"
        ) {
          TabItem(
              item = state.posts.sortedByDescending { it.likes },  //DataSource.communityPostData().sortedByDescending { it.likes },
              navController = navController
          )
        },
        MagicTabItem(
            title = "Terbaru"
        ) {
            TabItem(
                item = state.posts.sortedByDescending { it.timeDiff() },  //DataSource.communityPostData().sortedBy { it.timeDiff() },
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
    item: List<CommunityPostSQL>,
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

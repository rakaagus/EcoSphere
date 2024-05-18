package com.neirasphere.ecosphere.ui.screen.community

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.ui.components.PostLayout

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CommunityScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 0.dp)
    ) {
        items(DataSource.communityPostData().size) { index ->
            PostLayout(post = DataSource.communityPostData()[index], navController)
            Divider()
        }
    }
}
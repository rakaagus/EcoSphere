package com.neirasphere.ecosphere.ui.screen.community

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.ui.components.MagicTabItem
import com.neirasphere.ecosphere.ui.components.MagicTabLayout
import com.neirasphere.ecosphere.ui.components.PostLayout
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CommunityScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        MagicTabItem(title = "Populer") { },
        MagicTabItem(title = "Terbaru") { }
    )

    LazyColumn(
        modifier = Modifier.padding(bottom = 0.dp)
    ) {
        item {
            MagicTabLayout(
                modifier = Modifier.layoutId("tablayout"),
                tabList = tabs,
                tabIndicatorColor = PrimaryColor
            )
        }
        items(DataSource.communityPostData().size) { index ->
            PostLayout(post = DataSource.communityPostData()[index], navController)
            Divider()
        }
    }
}
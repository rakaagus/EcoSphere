package com.neirasphere.ecosphere.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun MagicTabLayout(
    modifier: Modifier = Modifier,
    tabList: List<MagicTabItem>,
    tabColor: Color = Color.White,
    tabIndicatorColor: Color = Color.Blue,
    initialPage: Int = 0,
) {

    val pagerState = rememberPagerState(initialPage = initialPage)

    Column(
        modifier = modifier.background(tabColor)
    ) {
        MagicTabs(pagerState = pagerState, tabList = tabList, indicatorColor = tabIndicatorColor)
        MagicTabsContent(pagerState = pagerState, tabList = tabList)
    }

}

typealias ComposableFun = @Composable () -> Unit

data class MagicTabItem(
    var title: String,
    var screen: ComposableFun
)

@ExperimentalPagerApi
@Composable
fun MagicTabs(
    pagerState: PagerState,
    tabList: List<MagicTabItem>,
    indicatorColor: Color = Color.Black,
) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = indicatorColor
            )
        }
    ) {

        tabList.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        tabList[index].title,
                        color = if (pagerState.currentPage == index) Color.Black else Color.Gray,
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MagicTabsContent(tabList: List<MagicTabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabList.size) { page ->
        tabList[page].screen()
    }
}
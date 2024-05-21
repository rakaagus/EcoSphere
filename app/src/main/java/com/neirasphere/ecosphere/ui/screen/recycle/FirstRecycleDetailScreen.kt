package com.neirasphere.ecosphere.ui.screen.recycle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.model.FirstRecycleData
import com.neirasphere.ecosphere.ui.RecycleViewModelFactory
import com.neirasphere.ecosphere.ui.common.UiState
import com.neirasphere.ecosphere.ui.components.FirstRecycleCard
import com.neirasphere.ecosphere.ui.theme.InActiveColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun FirstRecycleScreen(
    recycleCategoryId : Long,
    onClickDetail: (Long) -> Unit,
    viewModel: FirstRecycleViewModel = viewModel(
        factory = RecycleViewModelFactory(Injection.provideRecycleRepository())
    ),
    modifier: Modifier = Modifier,
){
    FirstRecycleContent(recycleCategoryId = recycleCategoryId, onClickDetail = onClickDetail, viewModel = viewModel)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FirstRecycleContent(
    recycleCategoryId: Long,
    onClickDetail: (Long) -> Unit,
    viewModel : FirstRecycleViewModel,
    modifier : Modifier = Modifier,
) {
    LaunchedEffect(recycleCategoryId) {
        viewModel.getFirstRecycle(recycleCategoryId)
    }

//    Log.d("FirstRecycleContent", "recycleCategoryId: $recycleCategoryId")

    val firstRecycleState by viewModel.firstRecycleState.collectAsState()

    val carousel = listOf(
        RecycleCarousel.First,
        RecycleCarousel.Second,
        RecycleCarousel.Third,
    )

    val pagerState = rememberPagerState(
        initialPage = 0
    )

    Column(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
        ) { position ->
            CarouselItem(carousel = carousel[position])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            inactiveColor = InActiveColor,
            activeColor = PrimaryColor,
            spacing = 5.dp,
            indicatorHeight = 10.dp,
            indicatorWidth = 10.dp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
        when (firstRecycleState) {
            is UiState.Loading -> {
                // Display a loading indicator
            }

            is UiState.Error -> {
                // Display an error message
            }

            is UiState.Success -> {
                val data = (firstRecycleState as UiState.Success<List<FirstRecycleData>>).data

                LazyColumn(
                    modifier = Modifier
                        .height(500.dp)
                ) {
                    items(data) {

                        FirstRecycleCard(
                            firstRecycleData = it,
                            onClickDetail = onClickDetail
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CarouselItem(
    carousel: RecycleCarousel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(204.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    carousel.title,
                    style = MaterialTheme.typography.labelLarge.copy(color = NeutralColorWhite),
                    modifier = Modifier
                        .padding(top = 28.dp)
                )
                Text(
                    carousel.description,
                    style = MaterialTheme.typography.bodyMedium.copy(color = NeutralColorWhite),
                    modifier = Modifier
                        .padding(top = 28.dp)
                )
            }
        }

        Image(
            painter = painterResource(id = carousel.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 180.dp, height = 114.dp)
                .padding(end = 30.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.TopEnd)
//                .offset(y = -50.dp) // Mengatur offset vertikal agar gambar setengah berada di luar card
        )
    }
}

//    viewModel.firstRecycleState.collectAsState(initial = UiState.Loading).value.let { state ->
//        when (state) {
//            is UiState.Loading -> {
//                viewModel.getFirstRecycle(recycleCategoryId)
//            }
//
//            is UiState.Error -> {
//
//            }
//
//            is UiState.Success -> {
//                LazyColumn(
//                    modifier = modifier
//                        .padding(12.dp)
//                ) {
//                    //Holder bagian atas -> cb liat dari open screen
//                    //Card dibuat dari lazy column
//                    items(state.data){
//                        FirstRecycleCard(
//                            firstRecycleData = it,
//                            onClickDetail = onClickDetail
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}

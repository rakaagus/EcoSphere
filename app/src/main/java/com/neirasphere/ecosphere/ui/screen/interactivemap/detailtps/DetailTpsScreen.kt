package com.neirasphere.ecosphere.ui.screen.interactivemap.detailtps

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.model.MapData
import com.neirasphere.ecosphere.ui.MapViewModelFactory
import com.neirasphere.ecosphere.ui.ViewModelFactory
import com.neirasphere.ecosphere.ui.common.UiState
import com.neirasphere.ecosphere.ui.components.MagicTabItem
import com.neirasphere.ecosphere.ui.components.MagicTabLayout
import com.neirasphere.ecosphere.ui.components.PostActions
import com.neirasphere.ecosphere.ui.components.PostAndImage
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import java.nio.channels.FileChannel.MapMode

@Composable
fun DetailTpsScreen(
    tpsId: Long,
    navController: NavController,
    viewModel: DetailTpsViewModel = viewModel(
        factory = MapViewModelFactory(Injection.provideMapRepository())
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        DetailTpsContext(
            tpsId = tpsId,
            viewModel = viewModel,
            navController = navController
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailTpsContext(
    tpsId: Long,
    viewModel: DetailTpsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    viewModel.detailTpsUiState.collectAsState(initial = UiState.Loading).value.let { UiState ->

        when (UiState) {
            is UiState.Loading -> {
                viewModel.getDetailTps(tpsId)
            }

            is UiState.Error -> {

            }

            is UiState.Success -> {
                val data = UiState.data

                val detailTpsTabs = listOf(
                    MagicTabItem(
                        title = "Gallery"
                    ) {
                        TabGallery(galleryList = data.image)
                    },
                    MagicTabItem(
                        title = "About"
                    ) {
                        TabAbout(dataTps = data)
                    },
                )

                Box {
                    Image(
                        painter = painterResource(id = data.image[0]),
                        contentDescription = data.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .clip(RoundedCornerShape(10))
                    )
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = PrimaryColor
                        ),
                        modifier = Modifier
                            .size(34.dp)
                            .offset(x = 16.dp, y = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = data.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = data.rating.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color(0xFF3D3D3D)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = data.rating.toString(),
                        tint = Color(0xFFFFB015)
                    )
                }
                Text(
                    text = data.detailLocation,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF3D3D3D),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                MagicTabLayout(
                    modifier = Modifier.layoutId("tablayout"),
                    tabList = detailTpsTabs,
                    tabIndicatorColor = PrimaryColor
                )
            }
        }
    }
}

@Composable
fun TabGallery(
    galleryList: List<Int>,
    modifier: Modifier = Modifier
) {
    Column {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Text(text = "Gallery")
            }
            items(galleryList) {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "",
                    modifier = Modifier.clip(
                        RoundedCornerShape(10.dp)
                    )
                )
            }
        }
    }
}

@Composable
fun TabAbout(
    dataTps: com.neirasphere.ecosphere.domain.model.MapData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SectionTextTpsDetail(title = R.string.title_tps_detail_desc) {
            Text(text = dataTps.description, style = MaterialTheme.typography.bodySmall, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionTextTpsDetail(title = R.string.title_tps_detail_open) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(50.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(dataTps.openLocation) {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionTextTpsDetail(title = R.string.title_tps_detail_fasil) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(50.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(dataTps.facilities) {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionTextTpsDetail(title = R.string.title_tps_detail_available_trash) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(50.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(dataTps.trashVariantAvailable) {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun SectionTextTpsDetail(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = title), style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(bottom = 10.dp)
        )
        content()
        Spacer(modifier = Modifier.height(10.dp))
        Divider()
    }
}
    



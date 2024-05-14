package com.neirasphere.ecosphere.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.components.HomeAppBar
import com.neirasphere.ecosphere.ui.components.HomeCardClassify
import com.neirasphere.ecosphere.ui.components.SearchBar
import com.neirasphere.ecosphere.ui.components.SectionTextColumn
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.ui.ViewModelFactory
import com.neirasphere.ecosphere.ui.common.UiState
import com.neirasphere.ecosphere.ui.components.HomeCategoriesLearnCard

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    modifier: Modifier = Modifier
) {
    HomeContent(viewModel = viewModel, navController = navController, modifier = modifier)
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeAppBar(name = "Erlin", location = "Cipayung, Bekasi", navController = navController)
        SearchBar(query = "", onQueryChange = {}, modifier = Modifier.padding(horizontal = 16.dp))
        HomeCardClassify("10", "20", "30")
        SectionTextColumn(title = R.string.section_one, modifier = Modifier.padding(top = 25.dp)) {
            viewModel.categoryLearn.collectAsState(initial = UiState.Loading).value.let { state ->
                when (state) {
                    is UiState.Loading -> {
                        viewModel.getLearnHome()
                    }

                    is UiState.Error -> {}
                    is UiState.Success -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.height(450.dp)
                        ) {
                            items(state.data) {
                                HomeCategoriesLearnCard(categoriesLearn = it)
                            }
                        }
                    }
                }
            }
        }
        SectionTextColumn(
            title = R.string.section_two,
            modifier = Modifier.padding(top = 25.dp, bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.map_image),
                contentDescription = "Map",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController())
}
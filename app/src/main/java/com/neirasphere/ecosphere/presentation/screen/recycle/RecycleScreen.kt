package com.neirasphere.ecosphere.presentation.screen.recycle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.presentation.RecycleViewModelFactory
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.RecycleCategoryCard
import com.neirasphere.ecosphere.presentation.components.SearchBar

@Composable
fun RecycleScreen(
    onClickDetail: (Long) -> Unit,
    viewModel: RecycleViewModel = viewModel(
        factory = RecycleViewModelFactory(Injection.provideRecycleRepository())
    ),
    modifier: Modifier = Modifier,
){
    RecycleContent(onClickDetail = onClickDetail, viewModel = viewModel)
}

@Composable
fun RecycleContent(
    onClickDetail: (Long) -> Unit,
    viewModel : RecycleViewModel,
    modifier : Modifier = Modifier,
){
    viewModel.recycleCategoryState.collectAsState(initial = UiState.Loading).value.let { state ->
        when(state){
            is UiState.Loading ->{
                viewModel.getRecycleCategoryState()
            }
            is UiState.Error -> {

            }
            is UiState.Success -> {
                Column (
                    modifier  = modifier
                        .padding(12.dp)
                ){
                    //searchbar
                    SearchBar(
                        query = "",
                        onQueryChange = {},
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 12.dp, bottom = 18.dp)
                    )
                    //Text Pilih Kategori
                    Text(
                        text = "Pilih Kategori",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(bottom = 8.dp, start = 12.dp, end = 12.dp)
                    )

                    //2 lazy column diluarnya ada row.
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(8.dp),
//                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.data){
                            RecycleCategoryCard(recycleCategoryData = it, onClickDetail = onClickDetail, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}




package com.neirasphere.ecosphere.presentation.screen.recycle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.domain.model.SecondRecycleData
import com.neirasphere.ecosphere.presentation.RecycleViewModelFactory
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.navigation.Screen

@Composable
fun SecondRecycleScreen(
    firstRecycleCategoryId : Int,
    navHostController: NavHostController,
    viewModel: SecondRecycleViewModel = viewModel(
        factory = RecycleViewModelFactory(Injection.provideRecycleRepository())
    ),
    modifier: Modifier = Modifier,
){
    SecondRecycleContent(
        firstRecycleCategoryId = firstRecycleCategoryId,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

@Composable
fun SecondRecycleContent(
    firstRecycleCategoryId : Int,
    viewModel : SecondRecycleViewModel,
    navHostController: NavHostController,
    modifier : Modifier = Modifier,
) {
    val secondRecycleState by viewModel.secondRecycleState.collectAsState()

    viewModel.secondRecycleState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getSecondRecycleState(firstRecycleCategoryId)
            }

            is UiState.Error -> {

            }

            is UiState.Success -> {
                val data = (secondRecycleState as UiState.Success<List<com.neirasphere.ecosphere.domain.model.SecondRecycleData>>).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(data) { item ->
                        SecondRecycleDetailContent(
                            secondRecycleData = item as SecondRecycleData.Content,
                            navHostController = navHostController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SecondRecycleDetailContent(
    secondRecycleData: com.neirasphere.ecosphere.domain.model.SecondRecycleData.Content,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .padding(12.dp)
    ) {
        Text(
            text = secondRecycleData.title,
            style = MaterialTheme.typography.labelMedium
        )
        Image(
            painterResource(id = secondRecycleData.image),
            contentDescription = null,
            modifier = Modifier
                .size(width = 320.dp, height = 130.dp)
                .align(Alignment.CenterHorizontally)
        )
        secondRecycleData.steps.forEach{
            StepItem(step = it)
        }
        Text(
            text = secondRecycleData.source,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
        )
        ButtonAuth(
            label = "Done",
            navHostController = navHostController,
            click = {navHostController.navigate(Screen.RecycleDoneScreen.route)})
    }
}

@Composable
fun StepItem(
    step : com.neirasphere.ecosphere.domain.model.Step,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = Modifier.padding(8.dp)
    ){
        Text(
            text = step.stepTitle,
            style = MaterialTheme.typography.labelSmall,
        )
        Text(
            text = step.stepDescription,
            style = MaterialTheme.typography.bodySmall
        )
        Image(
            painterResource(id = step.stepImage),
            contentDescription = null,
            modifier = Modifier
                .size(width = 200.dp, height = 140.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
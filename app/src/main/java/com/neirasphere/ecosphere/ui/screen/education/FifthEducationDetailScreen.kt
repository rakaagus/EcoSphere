package com.neirasphere.ecosphere.ui.screen.education

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.domain.model.FifthEducationData
import com.neirasphere.ecosphere.ui.EducationViewModelFactory
import com.neirasphere.ecosphere.ui.ViewModelFactory
import com.neirasphere.ecosphere.ui.common.UiState
import com.neirasphere.ecosphere.ui.components.ButtonEducation
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite

@Composable
fun FifthEducationDetailScreen(
    fourthEducationId : Long,
    navController : NavController,
    viewModel: FifthEducationViewModel = viewModel(
        factory = EducationViewModelFactory(Injection.provideEducationRepository())
    ),
    modifier : Modifier = Modifier,
){
    FifthEducationDetailContent(fifthEducationId = fourthEducationId, navController = navController , viewModel = viewModel )
}

@Composable
fun FifthEducationDetailContent(
    fifthEducationId : Long,
    navController : NavController,
    viewModel: FifthEducationViewModel,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        viewModel.fifthEducation.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getFifthEducation(fifthEducationId)
                }
                is UiState.Error -> {

                }
                is UiState.Success -> {
                    state.data.forEach { data ->
                        FifthEducationContent(fifthEducationData = data)
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        ButtonEducation(
                            label = "Bagian 5",
                            navController = navController,
                            onClick = { navController.navigate(Screen.EducationDoneScreen.route) })
                    }
                }
            }
        }
    }
}

@Composable
fun FifthEducationContent(
    fifthEducationData: com.neirasphere.ecosphere.domain.model.FifthEducationData,
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)
    ){
        Text(
            text = fifthEducationData.title,
            style = MaterialTheme.typography.labelLarge,
            color = BlackColor,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = fifthEducationData.firstDesc,
            style = MaterialTheme.typography.bodyMedium,
            color = BlackColor,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = fifthEducationData.firstImage),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
                .width(270.dp)
                .height(225.dp)
        )
        fifthEducationData.secondDesc?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        fifthEducationData.secondImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = painterResource(id = fifthEducationData.secondImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(270.dp)
                    .height(225.dp)
            )
        }

        fifthEducationData.thirdDesc?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = BlackColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        fifthEducationData.fourthDesc?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = BlackColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Text(
            text = fifthEducationData.lastDesc,
            style = MaterialTheme.typography.labelMedium,
            color = BlackColor,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
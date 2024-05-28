package com.neirasphere.ecosphere.presentation.screen.education

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.presentation.EducationViewModelFactory
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.ButtonEducation
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun FourthEducationDetailScreen(
    thirdEducationId : Long,
    navController: NavController,
    onClickDetail : (Long) -> Unit,
    viewModel: FourthEducationViewModel = viewModel(
        factory = EducationViewModelFactory(Injection.provideEducationRepository())
    ),
    modifier : Modifier = Modifier,
){
    FourthEducationDetailContent(fourthEducationId = thirdEducationId, navController = navController, onClickDetail = onClickDetail, viewModel = viewModel )
}

@Composable
fun FourthEducationDetailContent(
    fourthEducationId : Long,
    navController: NavController,
    onClickDetail: (Long) -> Unit,
    viewModel: FourthEducationViewModel,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        viewModel.fourthEducation.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getFourthEducation(fourthEducationId)
                }

                is UiState.Error -> {

                }

                is UiState.Success -> {
                    state.data.forEach { data ->
                        FourthEducationContent(fourthEducationData = data)
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        ButtonEducation(
                            label = "Bagian 4",
                            onClick = { onClickDetail(data.id) },
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FourthEducationContent(
    fourthEducationData: com.neirasphere.ecosphere.domain.model.FourthEducationData,
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)
    ){
        Text(
            text = fourthEducationData.title,
            style = MaterialTheme.typography.labelLarge,
            color = BlackColor,
            modifier = Modifier.padding(top = 4.dp)
        )
        Image(
            painter = painterResource(id = fourthEducationData.firstImage),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
                .width(270.dp)
                .height(225.dp)
        )
        Text(
            text = fourthEducationData.firstDesc,
            style = MaterialTheme.typography.bodyMedium,
            color = BlackColor,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
        )
        fourthEducationData.secondImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = painterResource(id = fourthEducationData.secondImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(270.dp)
                    .height(225.dp)
            )
        }
        fourthEducationData.secondDesc?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        fourthEducationData.thirdImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(270.dp)
                    .height(225.dp)
            )
        }
        fourthEducationData.thirdDesc?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        fourthEducationData.fourthImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(270.dp)
                    .height(225.dp)
            )
        }
        fourthEducationData.fourthDesc?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        fourthEducationData.fifthImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(270.dp)
                    .height(225.dp)
            )
        }
        fourthEducationData.fifthDesc?.let {
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
            text = fourthEducationData.lastDesc,
            style = MaterialTheme.typography.labelMedium,
            color = BlackColor,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
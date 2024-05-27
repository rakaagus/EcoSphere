package com.neirasphere.ecosphere.ui.screen.education

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
import com.neirasphere.ecosphere.domain.model.SecondEducationData
import com.neirasphere.ecosphere.ui.EducationViewModelFactory
import com.neirasphere.ecosphere.ui.common.UiState
import com.neirasphere.ecosphere.ui.components.ButtonEducation
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun SecondEducationDetailScreen(
    firstEducationId: Long,
    navController: NavController,
    onClickDetail : (Long) -> Unit,
    viewModel: SecondEducationViewModel = viewModel(
        factory = EducationViewModelFactory(Injection.provideEducationRepository())
    ),
    modifier : Modifier = Modifier,
) {
    SecondEducationDetailContent(secEducationId = firstEducationId , onClickDetail = onClickDetail, navController = navController, viewModel = viewModel)
}

@Composable
fun SecondEducationDetailContent(
    secEducationId : Long,
    onClickDetail: (Long) -> Unit,
    navController: NavController,
    viewModel: SecondEducationViewModel,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        viewModel.secondEducation.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getSecondEducation(secEducationId)
                }
                is UiState.Error -> {

                }
                is UiState.Success -> {
                    state.data.forEach{ data ->
                        SecondEducationContent(secondEducationData = data)
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        ButtonEducation(
                            label = "Bagian 2",
                            onClick = { onClickDetail(data.id)},
                            navController = navController,)
                    }
                }
            }
        }
    }
}

@Composable
fun SecondEducationContent(
    secondEducationData: com.neirasphere.ecosphere.domain.model.SecondEducationData,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)

    ) {
        Text(
            text = secondEducationData.title,
            style = MaterialTheme.typography.labelLarge,
            color = BlackColor,
            modifier = Modifier.padding(top = 4.dp)
        )
        Image(
            painter = painterResource(id = secondEducationData.firstImage),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
                .width(210.dp)
                .height(190.dp)
        )
        Text(
            text = secondEducationData.desc,
            style = MaterialTheme.typography.bodyMedium,
            color = BlackColor,
            modifier = Modifier.padding(top = 12.dp)
        )
        if (secondEducationData.secondDesc != null) {
            Text(
                text = secondEducationData.secondDesc,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        secondEducationData.secondImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        //long description blm masuk
        if (secondEducationData.thirdDesc != null) {
            Text(
                text = secondEducationData.thirdDesc,
                style = MaterialTheme.typography.bodyMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        secondEducationData.thirdImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

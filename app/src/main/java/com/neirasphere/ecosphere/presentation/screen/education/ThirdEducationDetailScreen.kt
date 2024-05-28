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
fun ThirdEducationDetailScreen (
    secondEducationId: Long,
    navController: NavController,
    onClickDetail : (Long) -> Unit,
    viewModel: ThirdEducationViewModel = viewModel(
        factory = EducationViewModelFactory(Injection.provideEducationRepository())
    ),
    modifier : Modifier = Modifier,
){
    ThirdEducationDetailContent(thirdEducationId = secondEducationId, navController = navController, onClickDetail = onClickDetail, viewModel = viewModel)
}

@Composable
fun ThirdEducationDetailContent (
    thirdEducationId: Long,
    navController: NavController,
    onClickDetail : (Long) -> Unit,
    viewModel: ThirdEducationViewModel,
    modifier : Modifier = Modifier,
){
    Column(
    modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
) {
    viewModel.thirdEducation.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getThirdEducation(thirdEducationId)
            }
            is UiState.Error -> {

            }
            is UiState.Success -> {
                state.data.forEach{ data ->
                    ThirdEducationContent(thirdEducationData = data)
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    ButtonEducation(
                        label = "Bagian 3",
                        onClick = { onClickDetail(data.id)},
                        navController = navController
                    )
                }
            }
        }
    }
}

}

@Composable
fun ThirdEducationContent(
    thirdEducationData: com.neirasphere.ecosphere.domain.model.ThirdEducationData,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)

    ) {
        Text(
            text = thirdEducationData.title,
            style = MaterialTheme.typography.labelLarge,
            color = BlackColor,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = thirdEducationData.desc,
            style = MaterialTheme.typography.labelMedium,
            color = BlackColor,
            modifier = Modifier.padding(top = 12.dp)
        )
        Image(
            painter = painterResource(id = thirdEducationData.firstImage),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
                .width(210.dp)
                .height(190.dp)
        )

        if (thirdEducationData.secondDesc != null) {
            Text(
                text = thirdEducationData.secondDesc,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        thirdEducationData.secondImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        if (thirdEducationData.thirdDesc != null) {
            Text(
                text = thirdEducationData.thirdDesc,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        thirdEducationData.thirdImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        if (thirdEducationData.fourthDesc != null) {
            Text(
                text = thirdEducationData.fourthDesc,
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        thirdEducationData.fourthImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if (thirdEducationData.fifthDesc != null) {
            Text(
                text = thirdEducationData.fifthDesc,
                style = MaterialTheme.typography.bodyMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

    }
}
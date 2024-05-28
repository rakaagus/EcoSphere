package com.neirasphere.ecosphere.presentation.screen.education

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

@Composable
fun EducationDetailScreen(
    educationId: Long,
    onClickDetail: (Long) -> Unit,
    viewModel: FirstEducationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    EducationDetailContent(educationId = educationId, onClickDetail = onClickDetail , viewModel = viewModel)
}

@Composable
fun EducationDetailContent(
    educationId: Long,
    onClickDetail: (Long) -> Unit,
    viewModel: FirstEducationViewModel,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ){
        viewModel.firstEducation.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getFirstEducation(educationId)
                }

                is UiState.Error -> {

                }

                is UiState.Success -> {
                    state.data.forEach{ data ->
                        FirstEducationContent(firstEducationData = data)
                        FirstButtonEducation(
                            label = "Bagian 1",
                            onClick = { onClickDetail(data.id) })
                    }
                }
            }
        }
    }
}

@Composable
fun FirstButtonEducation(
    label : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryColor
){
    Card(
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = modifier
            .padding(16.dp)
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Row{
            Image(
                painterResource(id = R.drawable.back_button_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Transparent),
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = label,
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                color = NeutralColorWhite,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painterResource(id = R.drawable.back_button_right),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onClick() }
            )
        }
    }
}

@Composable
fun FirstEducationContent(
    firstEducationData: com.neirasphere.ecosphere.domain.model.FirstEducationData,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)

    ) {
        Text(
            text = firstEducationData.title,
            style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
            color = BlackColor,
            modifier = Modifier.padding(top = 4.dp)
        )
        Image(
            painter = painterResource(id = firstEducationData.firstImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
                .width(210.dp)
                .height(190.dp)
        )
        Text(
            text = firstEducationData.desc,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = BlackColor,
            modifier = Modifier.padding(top = 12.dp)
        )
        if (firstEducationData.secondDesc != null) {
            Text(
                text = firstEducationData.secondDesc,
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        firstEducationData.secondImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if (firstEducationData.thirdDesc != null) {
            Text(
                text = firstEducationData.thirdDesc,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = BlackColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

    }
}
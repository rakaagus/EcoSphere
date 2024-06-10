package com.neirasphere.ecosphere.presentation.screen.education

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.domain.model.EduHistory
import com.neirasphere.ecosphere.presentation.common.UiState
import com.neirasphere.ecosphere.presentation.components.EducationCard
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite

@Composable
fun EducationScreen(
    onClickDetail: (Long) -> Unit,
    viewModel: EducationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    EducationContent(onClickDetail = onClickDetail, viewModel = viewModel, modifier = modifier, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationContent(
    onClickDetail: (Long) -> Unit,
    viewModel: EducationViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ){
        viewModel.education.collectAsState(initial = UiState.Loading).value.let { state ->
            when(state){
                is UiState.Loading -> {
                    viewModel.getEducation()
                }
                is UiState.Error -> {

                }
                is UiState.Success -> {
                    Card(shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(NeutralColorWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .clickable {
                                navController.navigate(Screen.EduHistoryScreen.route)
                            }
                        ,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hourglass),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(78.dp)
                                    .padding(12.dp)
                            )
                            Column(){
                                Text(
                                    text = "Riwayat Pembelajaran Kamu",
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Text(
                                    text = "Selamat sudah menyelesaikan pembelajarannya!",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                    Divider(
                        color = NeutralColorGrey,
                        thickness = 4.dp,
                        modifier = Modifier.padding(top = 18.dp, bottom = 18.dp)
                    )
                    LazyColumn (
                        verticalArrangement = Arrangement.spacedBy(18.dp),
                        modifier = Modifier.height((690.dp))
                    ){
                        items(state.data){
                            EducationCard(
                                educationData = it,
                                onClickDetail = onClickDetail
                            )
                        }
                    }
                }
            }
        }
    }
}

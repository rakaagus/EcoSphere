package com.neirasphere.ecosphere.presentation.screen.education

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neirasphere.ecosphere.domain.model.EduHistory
import com.neirasphere.ecosphere.domain.repository.EduHistoryList
import com.neirasphere.ecosphere.presentation.components.EduHistoryCard

@Composable
fun EduHistoryScreen(
    viewModel: EduHistoryViewModel = hiltViewModel(),
) {

   val eduHistoryList by viewModel.eduHistoryList.collectAsState(
       initial = emptyList()
   )

    Column(
        modifier = Modifier.padding(12.dp)
    ){
        EduHistoryContent(eduHistoryList = eduHistoryList)
    }
}

@Composable
fun EduHistoryContent(
    eduHistoryList: EduHistoryList,
) {
   LazyColumn(
       modifier = Modifier
           .fillMaxSize()
           .padding(bottom = 12.dp)
   ) {
       items(
           items = eduHistoryList
       ) { eduHistory ->
           EduHistoryCard(eduHistory = eduHistory)
       }
   }
}

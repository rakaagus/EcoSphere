package com.neirasphere.ecosphere.presentation.screen.classification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.neirasphere.ecosphere.data.local.entities.ClassifyHistory
import com.neirasphere.ecosphere.domain.repository.AllClassifyHistory
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite

/*
"battery", "glass", "metal", "organic", "paper", "plastic"
organik: "paper", "organic"
anorganik: "battery", "glass", "metal", "plastic"
 */

@Composable
fun ClassifyHistoryScreen (
    viewModel: ClassifyHistoryViewModel = hiltViewModel(),
){
    val allClassifyHistory by viewModel.allClassifyHistory.collectAsState(
        initial = emptyList()
    )
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        ClassifyHistoryContent(
            allClassifyHistory = allClassifyHistory
        )
    }
}


@Composable
fun ClassifyHistoryContent(
    allClassifyHistory: AllClassifyHistory,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
    ) {
        items(
            items = allClassifyHistory
        ) { classifyHistory ->
            ClassifyHistoryCard(
                classifyHistory = classifyHistory
            )
        }
    }
}

@Composable
fun ClassifyHistoryCard(
    classifyHistory: ClassifyHistory,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(NeutralColorWhite),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            AsyncImage(
                model = classifyHistory.image,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = classifyHistory.title,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
package com.neirasphere.ecosphere.presentation.screen.classification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import java.io.File

/*
"battery", "glass", "metal", "organic", "paper", "plastic"
organik: "paper", "organic"
anorganik: "battery", "glass", "metal", "plastic"
 */

@Composable
fun ClassifyHistoryScreen (
    file: File?,
    viewModel: ClassifyHistoryViewModel = hiltViewModel(),
){
    val allClassifyHistory by viewModel.allClassifyHistory.collectAsState(
        initial = emptyList()
    )

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxHeight()
    ){
        ClassifyHistoryContent(
            file = file,
            allClassifyHistory = allClassifyHistory,
        )
    }
}

@Composable
fun ClassifyHistoryContent(
    file: File?,
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
                file = file,
                classifyHistory = classifyHistory
            )
        }
    }
}

@Composable
fun ClassifyHistoryCard(
    file: File?,
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
                model = file,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
            )
//            Image(
//                painter = painterResource(id = classifyHistory.image),
//                contentDescription = null,
//                modifier = Modifier.size(50.dp)
//            )
            Text(
                text = classifyHistory.title,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
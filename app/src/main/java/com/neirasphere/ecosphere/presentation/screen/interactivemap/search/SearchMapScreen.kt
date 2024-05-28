package com.neirasphere.ecosphere.presentation.screen.interactivemap.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.presentation.components.SearchBar
import com.neirasphere.ecosphere.presentation.components.SearchMapComponent

@Composable
fun SearchMapScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var dataSearch = remember {
        mutableStateListOf(
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
            com.neirasphere.ecosphere.domain.model.SearchModel(
                "TPST Piyungan",
                "Ngablak, Sitimulyo, Kabupaten Ba..."
            ),
        )
    }


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchMapContent(dataSearch)
    }
}

@Composable
fun SearchMapContent(
    data: List<com.neirasphere.ecosphere.domain.model.SearchModel>,
    modifier: Modifier = Modifier
) {
    SearchBar(query = "", onQueryChange = {}, modifier = modifier.padding(horizontal = 16.dp))
    LazyColumn {
        item {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "User Current Location")
            }
            Divider()
        }
        items(data) {
            SearchMapComponent(it.title, it.desc)
        }
    }
}
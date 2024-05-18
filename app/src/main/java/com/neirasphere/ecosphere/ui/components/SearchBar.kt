package com.neirasphere.ecosphere.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.model.CommunityPost
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color(0xFF434343)
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = NeutralColorGrey
        ),
        placeholder = {
            Text(
                text = "Cari di sini",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFC1C1C1)
            )
        },
        modifier = modifier.fillMaxWidth()
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitySearchBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    posts: List<CommunityPost> = DataSource.communityPostData()
) {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

    SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        leadingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            } else {
                Icon(
                    modifier = Modifier.clickable { navController.navigateUp() },
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            }
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xFF434343)
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = NeutralColorGrey
        ),
        placeholder = {
            Text(
                text = "Cari di sini",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFC1C1C1)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(36.dp))
    ) {
        LazyColumn {
            items(posts, key = { it.id }) {
                Row(
                    modifier = modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        tint = Color(0xFF434343),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    Column {
                        Text(
                            text = it.text,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF434343)
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        LazyRow {
                            items(4) {
                                Image(
                                    painter = painterResource(id = R.drawable.pot_handuk_2),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                            }
                        }
                    }

                }
            }
        }
    }
}
package com.neirasphere.ecosphere.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.domain.model.CategoryLearn
import com.neirasphere.ecosphere.data.local.entities.EduHistory
import com.neirasphere.ecosphere.presentation.screen.education.EduHistoryViewModel
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.ui.theme.TextColorSc

@Composable
fun HomeCardClassify(
    count: String,
    inorganic: String,
    organic: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(PrimaryColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 25.dp, start = 15.dp, end = 10.dp, bottom = 35.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(end = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash_icon_normal),
                        contentDescription = "count_classify",
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.count_classify, count),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
                // Vertical Divider
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .height(60.dp)
                        .width(1.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash_icon_anorganic),
                        contentDescription = "count_classify",
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.count_anorganic, inorganic),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trash_icon_organic),
                        contentDescription = "count_classify",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.count_organic, organic),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }

            }
        }
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(NeutralColorGrey),
            modifier = Modifier.offset(y = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Riwayat Klasifikasi Sampahmu",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun HomeCategoriesLearnCard(
    categoriesLearn: com.neirasphere.ecosphere.domain.model.CategoryLearn,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(NeutralColorWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = categoriesLearn.imageIcon),
                contentDescription = categoriesLearn.title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = categoriesLearn.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun HomeCardClassifyPrev() {
    HomeCardClassify(
        "10", "20", "30"
    )
}

@Preview
@Composable
private fun HomeCategoriesLearnCardPrev() {
    HomeCategoriesLearnCard(
        CategoryLearn(
            1, "Sampah Anorganik", R.drawable.item_home_1
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationCard(
    educationData : com.neirasphere.ecosphere.domain.model.EducationData,
    onClickDetail : (Long) -> Unit,
    viewModel: EduHistoryViewModel = hiltViewModel(),
    modifier : Modifier = Modifier
){
    Card(shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(NeutralColorWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth(),
//            .clickable {
//                onClickDetail(educationData.id)
//            },
        onClick = {
            val eduHistory = EduHistory(educationData.id, educationData.title, educationData.image)
            viewModel.addEduHistory(eduHistory)
            onClickDetail(educationData.id)
        }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = educationData.image),
                contentDescription = educationData.title,
                modifier = Modifier
                    .size(120.dp)
                    .padding(12.dp)
            )
            Text(
                text = educationData.title,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
fun FirstRecycleCard(
    firstRecycleData: com.neirasphere.ecosphere.domain.model.FirstRecycleData,
    onClickDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
){
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(NeutralColorWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onClickDetail(firstRecycleData.id.toLong())
            }
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 4.dp, end = 12.dp)
        ){
            Image(
                painter = painterResource(id = firstRecycleData.image),
                contentDescription = firstRecycleData.title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
            )
            Column (
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)

            ){
                Text(
                    text = firstRecycleData.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = BlackColor,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = firstRecycleData.updated,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColorSc,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.7f))
            Image(
                painterResource(id = R.drawable.icon_right_outline),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}
@Composable
fun RecycleCategoryCard(
    recycleCategoryData : com.neirasphere.ecosphere.domain.model.RecycleCategoryData,
    onClickDetail : (Long) -> Unit,
    modifier : Modifier = Modifier
){
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(NeutralColorWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClickDetail(recycleCategoryData.id)
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = recycleCategoryData.image),
                contentDescription = recycleCategoryData.title,
                modifier = Modifier
                    .size(64.dp)
                    .padding(12.dp)
            )
            Text(
                text = recycleCategoryData.title,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
fun EduHistoryCard(
    eduHistory: EduHistory,
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
            Image(
                painter = painterResource(id = eduHistory.image),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = eduHistory.title,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
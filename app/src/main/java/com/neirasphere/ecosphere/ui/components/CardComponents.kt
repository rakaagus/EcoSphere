package com.neirasphere.ecosphere.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.model.CategoryLearn
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor

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
                    Image(
                        painter = painterResource(id = R.drawable.trash_icon_normal),
                        contentDescription = "count_classify",
                        modifier = Modifier.size(40.dp)
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
                    Image(
                        painter = painterResource(id = R.drawable.trash_icon_anorganic),
                        contentDescription = "count_classify",
                        modifier = Modifier.size(40.dp)
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
    categoriesLearn: CategoryLearn,
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
package com.neirasphere.ecosphere.presentation.screen.education

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import androidx.compose.material3.Text
import androidx.compose.runtime.*


@Composable
fun EducationDone(
    navController: NavHostController,
    modifier : Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.edu_done),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp, 280.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Asiik!! Kamu berhasil menyelesaikan satu pembelajaranmu! Belajar materi yang lain lagi yuk!",
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(12.dp)
            )
            DoneButton(
                labelEdu = "Belajar lagi",
                labelHome = "Nggak dulu",
                onClickEdu = { navController.navigate(Screen.EducationScreen.route) },
                onClickHome = { navController.navigate(Screen.HomeScreen.route) }
            )

        }
    }
}

@Composable
fun DoneButton(
    labelEdu : String,
    labelHome : String,
    onClickEdu : () -> Unit,
    onClickHome : () -> Unit,
    modifier: Modifier = Modifier
){
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Column {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 500)
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 1000)
            )
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    visible = false
                    onClickEdu()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = labelEdu,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeutralColorWhite
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 500)
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 500)
            )
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    visible = false
                    onClickHome()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(50.dp)
            ) {
                androidx.compose.material3.Text(
                    text = labelHome,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeutralColorWhite
                )
            }
        }
    }
}
package com.neirasphere.ecosphere.ui.screen.recycle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite

@Composable
fun RecycleDone (
    navController: NavHostController,
    modifier : Modifier = Modifier,
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Column() {
            Image(
                painterResource(id = R.drawable.recycle_13),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Yey!! Kamu berhasil menyelesaikan satu aksi hijau untuk menyelamatkan bumi!",
                style = MaterialTheme.typography.labelMedium,
                color = BlackColor,
                modifier = Modifier.padding(12.dp)
            )
            //Tambah button
            RecycleButtonDone(
                label = "Done",
                onClick = { navController.navigate(Screen.RecycleScreen.route) })
        }
    }
}

@Composable
fun RecycleButtonDone(
    label : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(){
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn (
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 1000)
            )
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    onClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeutralColorWhite
                )
            }
        }
    }
}
package com.neirasphere.ecosphere.presentation.screen.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.TextColorSc
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.presentation.OnboardingViewModelFactory

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = viewModel(
        factory = OnboardingViewModelFactory(Injection.provideAppRepo(LocalContext.current))
    ),
    modifier: Modifier = Modifier
) {
    var startAnim by remember {
        mutableStateOf(false)
    }

    val statusLogin = viewModel.getStatusOnboarding().collectAsState(initial = false)

    LaunchedEffect(key1 = true) {
        delay(1500)
        startAnim = true
        delay(2000)

        if(statusLogin.value){
            navController.navigate(Screen.HomeScreen.route){
                popUpTo(Screen.SplashScreen.route){
                    inclusive = true
                }
            }
        }else {
            navController.navigate(Screen.OnboardingScreen.route){
                popUpTo(Screen.SplashScreen.route){
                    inclusive = true
                }
            }
        }
    }
    SplashScreenAnimation(state = startAnim)
}

@Composable
fun SplashScreenAnimation(
    state: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.logo_app_sc), contentDescription = stringResource(
                id = R.string.app_name
            ), modifier = Modifier
                .size(120.dp)
                .padding())
            AnimatedVisibility(visible = state) {
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = stringResource(id = R.string.splash_screen_animate_title), style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 15.sp
                    ), color = TextColorSc)
                    Text(text = stringResource(id = R.string.splash_screen_animate_desc), style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 11.sp
                    ), color = TextColorSc)
                }
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenAnimationPrev() {
    SplashScreenAnimation(false)
}
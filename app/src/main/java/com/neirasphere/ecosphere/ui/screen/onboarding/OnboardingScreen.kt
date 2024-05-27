package com.neirasphere.ecosphere.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.InActiveColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorWhite
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.utils.OnboardingPage
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neirasphere.ecosphere.di.Injection
import com.neirasphere.ecosphere.ui.OnboardingViewModelFactory
import com.neirasphere.ecosphere.ui.ViewModelFactory

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = viewModel(
        factory = OnboardingViewModelFactory(Injection.provideAppRepo(LocalContext.current))
    ),
    modifier: Modifier = Modifier
) {
    val page = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third,
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(10f)
        ) {position ->
            PagerScreen(onboardingPage = page[position])
        }
        Button(
            onClick = {
                scope.launch {
                    if(pagerState.currentPage == pagerState.pageCount - 1){
                        viewModel.saveStatusOnboarding(true)
                        navController.popBackStack()
                        navController.navigate(Screen.LoginScreen.route)
                    }else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.size(height = 40.dp, width = 120.dp),
        ) {
            val state = pagerState.currentPage
            Text(text = if (state == 2) "Start" else "Next", color = NeutralColorWhite)
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState,
            inactiveColor = InActiveColor,
            activeColor = PrimaryColor,
            spacing = 5.dp,
            indicatorHeight = 10.dp,
            indicatorWidth = 10.dp
        )
    }
}

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(397.dp),
            painter = painterResource(id = onboardingPage.image),
            contentDescription = "Pager Image"
        )
        Text(text = stringResource(id = onboardingPage.title), style = MaterialTheme.typography.headlineMedium.copy(
            fontSize = 22.sp
        ), color = BlackColor, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = stringResource(id = onboardingPage.description), style = MaterialTheme.typography.bodyLarge, color = BlackColor, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
private fun OnboardingPage1() {
    Column(Modifier.fillMaxSize()) {
        PagerScreen(onboardingPage = OnboardingPage.First)
    }
}


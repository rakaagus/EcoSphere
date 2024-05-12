package com.neirasphere.ecosphere

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neirasphere.ecosphere.ui.navigation.NavigationItem
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.screen.onboarding.OnboardingScreen
import com.neirasphere.ecosphere.ui.screen.splashscreen.SplashScreen

@Composable
fun EcoSphereApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.SplashScreen.route, Screen.OnboardingScreen.route, Screen.LoginScreen.route, Screen.RegisterScreen.route -> {}
            }
        },
        bottomBar = {
            when (currentRoute) {
                Screen.SplashScreen.route, Screen.OnboardingScreen.route, Screen.LoginScreen.route, Screen.RegisterScreen.route -> {}
                Screen.HomeScreen.route, Screen.EducationScreen.route, Screen.MapScreen.route, Screen.CommunityScreen.route -> {

                }
            }
        },
        floatingActionButton = {}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.SplashScreen.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.OnboardingScreen.route) {
                OnboardingScreen(navController = navController)
            }
            composable(Screen.LoginScreen.route) {

            }
            composable(Screen.RegisterScreen.route) {

            }
        }
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.title_home),
                icon = Icons.Filled.Home,
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.title_edikasi),
                icon = Icons.Default.Book,
                screen = Screen.EducationScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.title_map),
                icon = Icons.Default.Map,
                screen = Screen.ProfileScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.title_komunitas),
                icon = Icons.Default.People,
                screen = Screen.ProfileScreen
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        text = item.title
                    )
                }
            )
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = color)
    }
}
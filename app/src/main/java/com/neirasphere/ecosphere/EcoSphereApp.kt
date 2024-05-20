package com.neirasphere.ecosphere

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neirasphere.ecosphere.ui.components.CenterTopAppBar
import com.neirasphere.ecosphere.ui.components.CommunityAppBar
import com.neirasphere.ecosphere.ui.navigation.NavigationItem
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.screen.auth.login.LoginScreen
import com.neirasphere.ecosphere.ui.screen.auth.register.RegisterScreen
import com.neirasphere.ecosphere.ui.screen.community.CommunityScreen
import com.neirasphere.ecosphere.ui.screen.home.HomeScreen
import com.neirasphere.ecosphere.ui.screen.onboarding.OnboardingScreen
import com.neirasphere.ecosphere.ui.screen.profile.ProfileScreen
import com.neirasphere.ecosphere.ui.screen.splashscreen.SplashScreen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.ui.theme.containerColor
import androidx.compose.ui.res.painterResource
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import com.neirasphere.ecosphere.ui.components.CenterTopAppBar
import com.neirasphere.ecosphere.ui.screen.profile.ProfileScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.neirasphere.ecosphere.ui.screen.auth.changepassword.ChangePasswordScreen
import com.neirasphere.ecosphere.ui.screen.auth.verificationemail.VerificationEmailScreen
import com.neirasphere.ecosphere.ui.screen.education.EducationDetailScreen
import com.neirasphere.ecosphere.ui.screen.education.EducationDone
import com.neirasphere.ecosphere.ui.screen.education.EducationScreen
import com.neirasphere.ecosphere.ui.screen.education.FifthEducationDetailScreen
import com.neirasphere.ecosphere.ui.screen.education.FourthEducationDetailScreen
import com.neirasphere.ecosphere.ui.screen.education.SecondEducationDetailScreen
import com.neirasphere.ecosphere.ui.screen.education.ThirdEducationDetailScreen
import com.neirasphere.ecosphere.ui.screen.profile.edit.EditProfileScreen

@Composable
fun EcoSphereApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            when (currentRoute) {
                Screen.CommunityScreen.route -> {
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = PrimaryColor
                        ),
                        modifier = Modifier
                            .size(48.dp)
                            .shadow(8.dp, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        },
        topBar = {
            when (currentRoute) {
                Screen.ProfileScreen.route -> {
                    CenterTopAppBar(navController = navController, title = null)
                }

                Screen.CommunityScreen.route -> {
                    CommunityAppBar(navController = navController)
                }

                Screen.EditProfileScreen.route -> {
                    CenterTopAppBar(
                        navController = navController,
                        title = R.string.title_page_edit_profile
                    )
                }

                Screen.EducationScreen.route -> {
                    CenterTopAppBar(
                        navController = navController,
                        title = R.string.title_page_education
                    )
                }

                Screen.VerificationEmailScreen.route -> {
                    CenterTopAppBar(
                        navController = navController,
                        title = R.string.title_page_verif_eamil
                    )
                }

                Screen.ChangePasswordScreen.route -> {
                    CenterTopAppBar(
                        navController = navController,
                        title = R.string.title_page_change_password
                    )
                }
            }
        },
        bottomBar = {
            when (currentRoute) {
                Screen.HomeScreen.route, Screen.EducationScreen.route, Screen.MapScreen.route, Screen.CommunityScreen.route -> {
                    BottomAppBar(navController = navController)
                }
            }
        },
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
                LoginScreen(navController = navController)
            }
            composable(Screen.RegisterScreen.route) {
                RegisterScreen(navController = navController)
            }
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.EducationScreen.route) {
                EducationScreen(
                    onClickDetail = { educationId ->
                        navController.navigate(Screen.EducationDetailScreen.createRoute(educationId))
                    }
                )
            }
            composable(Screen.EducationDetailScreen.route,
                arguments = listOf(
                    navArgument("educationId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id = it.arguments?.getLong("educationId") ?: 1
                EducationDetailScreen(
                    onClickDetail = { firstEducationId ->
                        navController.navigate(
                            Screen.SecondEducationDetailScreen.createRoute(
                                firstEducationId
                            )
                        )
                    },
                    educationId = id
                )
            }
            composable(Screen.SecondEducationDetailScreen.route,
                arguments = listOf(
                    navArgument("firstEducationId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id = it.arguments?.getLong("firstEducationId") ?: 1
                SecondEducationDetailScreen(
                    onClickDetail = { secondEducationId ->
                        navController.navigate(
                            Screen.ThirdEducationDetailScreen.createRoute(
                                secondEducationId
                            )
                        )
                    },
                    firstEducationId = id,
                    navController = navController
                )
            }
            composable(Screen.ThirdEducationDetailScreen.route,
                arguments = listOf(
                    navArgument("secondEducationId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id = it.arguments?.getLong("secondEducationId") ?: 1
                ThirdEducationDetailScreen(
                    onClickDetail = { thirdEducationId ->
                        navController.navigate(
                            Screen.FourthEducationDetailScreen.createRoute(
                                thirdEducationId
                            )
                        )
                    },
                    secondEducationId = id,
                    navController = navController
                )
            }
            composable(Screen.FourthEducationDetailScreen.route,
                arguments = listOf(
                    navArgument("thirdEducationId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id = it.arguments?.getLong("thirdEducationId") ?: 1
                FourthEducationDetailScreen(
                    onClickDetail = { fourthEducationId ->
                        navController.navigate(
                            Screen.FifthEducationDetailScreen.createRoute(
                                fourthEducationId
                            )
                        )
                    },
                    thirdEducationId = id,
                    navController = navController
                )
            }
            composable(Screen.FifthEducationDetailScreen.route,
                arguments = listOf(
                    navArgument("fourthEducationId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id = it.arguments?.getLong("fourthEducationId") ?: 1
                FifthEducationDetailScreen(fourthEducationId = id, navController = navController)
            }
            composable(Screen.EducationDoneScreen.route) {
                EducationDone(navController = navController)
            }
            composable(Screen.MapScreen.route) {}
            composable(Screen.CommunityScreen.route) {
                CommunityScreen(navController = navController)
            }
            composable(Screen.ClassifyScreen.route) {}
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController)
            }
            composable(Screen.EditProfileScreen.route) {
                EditProfileScreen(navController = navController)
            }
            composable(Screen.ChangePasswordScreen.route) {
                ChangePasswordScreen(navController = navController)
            }
            composable(Screen.VerificationEmailScreen.route) {
                VerificationEmailScreen(navController = navController)
            }
        }
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val customColors = NavigationBarItemDefaults.colors(
        indicatorColor = containerColor,
        selectedIconColor = PrimaryColor,
        selectedTextColor = PrimaryColor,
        unselectedIconColor = BlackColor,
        unselectedTextColor = BlackColor
    )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp) // Bentuk bulat (rounded)
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = containerColor,
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
                    screen = Screen.MapScreen
                ),
                NavigationItem(
                    title = stringResource(id = R.string.title_komunitas),
                    icon = Icons.Default.People,
                    screen = Screen.CommunityScreen
                ),
            )
            navigationItems.forEachIndexed { index, item ->
                if (index == 2) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screen.ClassifyScreen.route)
                        },
                        backgroundColor = PrimaryColor,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.scan_icon),
                            contentDescription = "icon",
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
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
                            text = item.title, style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    colors = customColors
                )
            }
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
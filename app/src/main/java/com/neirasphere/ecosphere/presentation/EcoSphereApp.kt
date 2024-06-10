package com.neirasphere.ecosphere.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neirasphere.ecosphere.presentation.components.CenterTopAppBar
import com.neirasphere.ecosphere.presentation.components.CommunityAppBar
import com.neirasphere.ecosphere.presentation.components.PostingBottomBar
import com.neirasphere.ecosphere.presentation.navigation.NavigationItem
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.auth.login.LoginScreen
import com.neirasphere.ecosphere.presentation.screen.auth.register.RegisterScreen
import com.neirasphere.ecosphere.presentation.screen.home.HomeScreen
import com.neirasphere.ecosphere.presentation.screen.onboarding.OnboardingScreen
import com.neirasphere.ecosphere.presentation.screen.profile.ProfileScreen
import com.neirasphere.ecosphere.presentation.screen.splashscreen.SplashScreen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.ui.theme.containerColor
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.screen.auth.changepassword.ChangePasswordScreen
import com.neirasphere.ecosphere.presentation.screen.auth.verificationemail.VerificationEmailScreen
import com.neirasphere.ecosphere.presentation.screen.classification.ClassificationScreen
import com.neirasphere.ecosphere.presentation.screen.community.CommunityScreen
import com.neirasphere.ecosphere.presentation.screen.community.DetailPostScreen
import com.neirasphere.ecosphere.presentation.screen.community.DummyDetailPostScreen
import com.neirasphere.ecosphere.presentation.screen.community.PostingScreen
import com.neirasphere.ecosphere.presentation.screen.education.EduHistoryScreen
import com.neirasphere.ecosphere.presentation.screen.education.EducationDetailScreen
import com.neirasphere.ecosphere.presentation.screen.education.EducationDone
import com.neirasphere.ecosphere.presentation.screen.education.EducationScreen
import com.neirasphere.ecosphere.presentation.screen.education.FifthEducationDetailScreen
import com.neirasphere.ecosphere.presentation.screen.education.FourthEducationDetailScreen
import com.neirasphere.ecosphere.presentation.screen.education.SecondEducationDetailScreen
import com.neirasphere.ecosphere.presentation.screen.education.ThirdEducationDetailScreen
import com.neirasphere.ecosphere.presentation.screen.interactivemap.MapScreen
import com.neirasphere.ecosphere.presentation.screen.interactivemap.confirmMaps.ConfirmMapScreen
import com.neirasphere.ecosphere.presentation.screen.interactivemap.detailtps.DetailTpsScreen
import com.neirasphere.ecosphere.presentation.screen.interactivemap.search.SearchMapScreen
import com.neirasphere.ecosphere.presentation.screen.profile.edit.EditProfileScreen
import com.neirasphere.ecosphere.presentation.screen.recycle.FirstRecycleScreen
import com.neirasphere.ecosphere.presentation.screen.recycle.RecycleDone
import com.neirasphere.ecosphere.presentation.screen.recycle.RecycleScreen
import com.neirasphere.ecosphere.presentation.screen.recycle.SecondRecycleScreen

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
                        onClick = { navController.navigate(Screen.PostingScreen.route) },
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
                    CenterTopAppBar(onBackClick = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.ProfileScreen.route) {
                                inclusive = true
                            }
                        }
                    }, title = null)
                }

                Screen.CommunityScreen.route -> {
                    CommunityAppBar(navController = navController)
                }

                Screen.EditProfileScreen.route -> {
                    CenterTopAppBar(
                        onBackClick = {
                            navController.navigate(Screen.ProfileScreen.route) {
                                popUpTo(Screen.EditProfileScreen.route) {
                                    inclusive = true
                                }
                            }
                        },
                        title = R.string.title_page_edit_profile
                    )
                }

                Screen.EducationScreen.route -> {
                    CenterTopAppBar(
                        onBackClick = {
                            navController.navigateUp()
                        },
                        title = R.string.title_page_education
                    )
                }

                Screen.EduHistoryScreen.route ->{
                    CenterTopAppBar(
                        onBackClick = {
                            navController.popBackStack()
                            navController.navigateUp()
                        },
                        title = R.string.title_page_education_history
                    )
                }

                Screen.VerificationEmailScreen.route -> {
                    CenterTopAppBar(
                        onBackClick = {
                            navController.navigateUp()
                        },
                        title = R.string.title_page_verif_eamil
                    )
                }

                Screen.ChangePasswordScreen.route -> {
                    CenterTopAppBar(
                        onBackClick = {
                            navController.navigate(Screen.ProfileScreen.route){
                                popUpTo(Screen.ChangePasswordScreen.route){
                                    inclusive = true
                                }
                            }
                        },
                        title = R.string.title_page_change_password
                    )
                }

                Screen.SearchMapScreen.route -> {
                    CenterTopAppBar(
                        onBackClick = {
                            navController.navigateUp()
                        },
                        title = R.string.title_search_page
                    )
                }

                Screen.ClassifyScreen.route -> {
                    CenterTopAppBar(onBackClick = {
                        navController.navigate(Screen.HomeScreen.route){
                            popUpTo(Screen.ClassifyScreen.route){
                                inclusive = true
                            }
                        }
                    }, title = null, actionIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.icon_history_classify),
                            contentDescription = "",
                            modifier = Modifier
                                .size(34.dp)
                                .clickable {

                                }
                        )
                    })
                }
            }
        },
        bottomBar = {
            when (currentRoute) {
                Screen.HomeScreen.route, Screen.EducationScreen.route, Screen.MapScreen.route, Screen.CommunityScreen.route -> {
                    BottomAppBar(navController = navController)
                }

                Screen.PostingScreen.route -> {
                    PostingBottomBar()
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = modifier.padding(innerPadding)
        ) {
            /*Splash & Onboarding Route*/
            composable(Screen.SplashScreen.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.OnboardingScreen.route) {
                OnboardingScreen(navController = navController)
            }

            /*Auth Route*/
            composable(Screen.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
            composable(Screen.RegisterScreen.route) {
                RegisterScreen(navController = navController)
            }
            composable(Screen.ChangePasswordScreen.route) {
                ChangePasswordScreen(navController = navController)
            }
            composable(Screen.VerificationEmailScreen.route) {
                VerificationEmailScreen(navController = navController)
            }

            /*Home Screen Route*/
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }

            /*Education Route*/
            composable(Screen.EducationScreen.route) {
                EducationScreen(
                    onClickDetail = { educationId ->
                        navController.navigate(Screen.EducationDetailScreen.createRoute(educationId))
                    },
                    navController = navController
                )
            }
            composable(
                Screen.EducationDetailScreen.route,
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
            composable(
                Screen.SecondEducationDetailScreen.route,
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
            composable(
                Screen.ThirdEducationDetailScreen.route,
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
            composable(
                Screen.FourthEducationDetailScreen.route,
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
            composable(
                Screen.FifthEducationDetailScreen.route,
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
            composable(Screen.EduHistoryScreen.route) {
                EduHistoryScreen()
            }

            /*Map Route*/
            composable(Screen.MapScreen.route) {
                MapScreen(clickToDetailTps = { tpsId ->
                    navController.navigate(Screen.DetailTpsScreen.createRoute(tpsId))
                })
            }
            composable(
                Screen.DetailTpsScreen.route,
                arguments = listOf(
                    navArgument("tpsId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id = it.arguments?.getLong("tpsId") ?: 1
                DetailTpsScreen(tpsId = id, navController = navController)
            }
            composable(Screen.ConfirmMapScreen.route) {
                ConfirmMapScreen(navController = navController)
            }
            composable(Screen.SearchMapScreen.route) {
                SearchMapScreen(navController = navController)
            }

            /*Community Route*/
            composable(Screen.CommunityScreen.route) {
                CommunityScreen(navController = navController)
            }
            composable(Screen.PostingScreen.route) {
                PostingScreen(navController = navController)
            }
            composable(
                Screen.DetailPostScreen.route + "/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                DetailPostScreen(
                    navController = navController,
                    postId = navBackStackEntry.arguments?.getInt("postId")
                )
            }
            composable(Screen.DummyDetailPostScreen.route) {
                DummyDetailPostScreen(navController = navController)
            }

            /*Classify Route*/
            composable(Screen.ClassifyScreen.route) {
                ClassificationScreen(navController = navController)
            }

            /*Profile Route*/
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

            composable(Screen.RecycleScreen.route) {
                RecycleScreen(
                    onClickDetail = { recycleCategoryId ->
                        navController.navigate(
                            Screen.FirstRecycleDetailScreen.createRoute(
                                recycleCategoryId
                            )
                        )
                    }
                )
            }
            composable(
                Screen.FirstRecycleDetailScreen.route,
                arguments = listOf(
                    navArgument("recycleCategoryId") {
                        type = NavType.LongType
                    }
                )
            ) {
                val id: Long = it.arguments?.getLong("recycleCategoryId") ?: 1
                FirstRecycleScreen(
                    recycleCategoryId = id,
                    onClickDetail = { firstCategoryId ->
                        navController.navigate(
                            Screen.SecondRecycleScreen.createRoute(
                                firstCategoryId
                            )
                        )
                    },
                )
            }
            composable(
                Screen.SecondRecycleScreen.route,
                arguments = listOf(
                    navArgument("firstCategoryId") {
                        type = NavType.IntType
                    }
                )
            ) {
                val id: Int = it.arguments?.getInt("firstCategoryId") ?: 1
                SecondRecycleScreen(
                    firstRecycleCategoryId = id,
                    navHostController = navController
                )
            }
            composable(Screen.RecycleDoneScreen.route) {
                RecycleDone(navController = navController)
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
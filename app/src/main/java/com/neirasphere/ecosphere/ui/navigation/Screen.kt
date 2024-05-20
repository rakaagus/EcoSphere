package com.neirasphere.ecosphere.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object OnboardingScreen: Screen("onboarding_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object ProfileScreen: Screen("profile_screen")
    object DetailProfileScreen: Screen("profile_Screen")

    object HomeScreen: Screen("home_screen")
    object EducationScreen: Screen("education_screen")
    object MapScreen: Screen("map_screen")
    object CommunityScreen: Screen("community_screen")
    object DetailPostScreen: Screen("detail_post_screen")
    object DummyDetailPostScreen: Screen("dummy_detail_post_screen")
    object PostingScreen: Screen("posting_screen")
    object ClassifyScreen: Screen("classify_screen")
}
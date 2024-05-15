package com.neirasphere.ecosphere.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object OnboardingScreen: Screen("onboarding_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object ProfileScreen: Screen("profile_screen")
    object DetailProfileScree: Screen("profile_Screen")

    object HomeScreen: Screen("home_screen")
    object EducationScreen: Screen("education_screen")
    object EducationDetailScreen: Screen("education_detail_screen/{educationId}"){
        fun createRoute(educationId: Long) = "education_detail_screen/$educationId"
    }
    object SecondEducationDetailScreen: Screen("second_education_detail_screen/{firstEducationId}"){
        fun createRoute(firstEducationId: Long) = "second_education_detail_screen/$firstEducationId"
    }
    object ThirdEducationDetailScreen: Screen("third_education_detail_screen/{secondEducationId}"){
        fun createRoute(secondEducationId: Long) = "third_education_detail_screen/$secondEducationId"
    }
    object FourthEducationDetailScreen: Screen("fourth_education_detail_screen/{thirdEducationId}"){
        fun createRoute(thirdEducationId: Long) = "fourth_education_detail_screen/$thirdEducationId"
    }
    object FifthEducationDetailScreen: Screen("fifth_education_detail_screen/{fourthEducationId}"){
        fun createRoute(fourthEducationId: Long) = "fifth_education_detail_screen/$fourthEducationId"
    }
    object EducationDoneScreen: Screen("education_done_screen")
    object MapScreen: Screen("map_screen")
    object CommunityScreen: Screen("community_screen")
    object ClassifyScreen: Screen("classify_screen")
}
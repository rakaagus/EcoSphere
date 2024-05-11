package com.neirasphere.ecosphere.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.neirasphere.ecosphere.R

sealed class OnboardingPage(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    object First : OnboardingPage(
        image = R.drawable.environment,
        title = R.string.title_onboarding_page_1,
        description = R.string.desc_onboarding_page_1
    )

    object Second: OnboardingPage(
        image = R.drawable.eco_conscious,
        title = R.string.title_onboarding_page_2,
        description = R.string.desc_onboarding_page_2
    )

    object Third: OnboardingPage(
        image = R.drawable.nature,
        title = R.string.title_onboarding_page_3,
        description = R.string.desc_onboarding_page_3
    )
}
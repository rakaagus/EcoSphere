package com.neirasphere.ecosphere.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neirasphere.ecosphere.data.AppRepository
import com.neirasphere.ecosphere.ui.screen.home.HomeViewModel
import com.neirasphere.ecosphere.ui.screen.onboarding.OnboardingViewModel
import com.neirasphere.ecosphere.ui.screen.splashscreen.SplashViewModel

class OnboardingViewModelFactory(val repository: AppRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return OnboardingViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(SplashViewModel::class.java)){
            return SplashViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
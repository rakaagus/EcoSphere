package com.neirasphere.ecosphere.ui.screen.splashscreen

import androidx.lifecycle.ViewModel
import com.neirasphere.ecosphere.data.AppRepository

class SplashViewModel(private val appRepository: AppRepository): ViewModel() {

    fun getStatusOnboarding() = appRepository.getStatusOnboarding()

}
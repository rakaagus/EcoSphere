package com.neirasphere.ecosphere.presentation.screen.splashscreen

import androidx.lifecycle.ViewModel
import com.neirasphere.ecosphere.data.repository.AppRepositoryImpl
import com.neirasphere.ecosphere.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    fun getStatusOnboarding() = appRepository.getStatusOnboardingUser()
    fun getIsLogin() = appRepository.isLoggedIn()

}
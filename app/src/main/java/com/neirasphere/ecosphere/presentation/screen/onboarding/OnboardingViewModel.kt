package com.neirasphere.ecosphere.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OnboardingViewModel(private val appRepository: AppRepository): ViewModel() {

    suspend fun saveStatusOnboarding(status: Boolean){
        viewModelScope.launch {
            appRepository.saveStatusOnboarding(status)
        }
    }

}
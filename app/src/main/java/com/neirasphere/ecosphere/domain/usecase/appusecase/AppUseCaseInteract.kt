package com.neirasphere.ecosphere.domain.usecase.appusecase

import com.neirasphere.ecosphere.domain.preferences.AppPreferences
import com.neirasphere.ecosphere.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUseCaseInteract @Inject constructor(
    private val appRepository: AppRepository
) : AppUseCase{
    override fun getStatusOnboardingUser(): Flow<Boolean> = appRepository.getStatusOnboardingUser()

    override suspend fun saveStatusOnboardingUser(status: Boolean) = appRepository.saveStatusOnboardingUser(status)
}
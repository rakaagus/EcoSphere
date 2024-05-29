package com.neirasphere.ecosphere.domain.usecase.appusecase

import kotlinx.coroutines.flow.Flow

interface AppUseCase {
    fun getStatusOnboardingUser(): Flow<Boolean>

    suspend fun saveStatusOnboardingUser(status: Boolean)
}
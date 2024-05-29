package com.neirasphere.ecosphere.domain.repository


import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getStatusOnboardingUser(): Flow<Boolean>

    suspend fun saveStatusOnboardingUser(status: Boolean)
}
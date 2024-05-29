package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.preferences.AppDataStore
import com.neirasphere.ecosphere.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : AppRepository {
    override fun getStatusOnboardingUser(): Flow<Boolean> = appDataStore.getStatusOnboardingUser()

    override suspend fun saveStatusOnboardingUser(status: Boolean) {
        appDataStore.saveStatusOnboardingUser(status)
    }

}
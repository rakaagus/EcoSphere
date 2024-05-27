package com.neirasphere.ecosphere.data

import androidx.datastore.dataStore
import com.neirasphere.ecosphere.data.preferences.AppDataStore
import kotlinx.coroutines.flow.Flow

class AppRepository (private val appDataStore: AppDataStore){

    fun getStatusOnboarding() : Flow<Boolean> = appDataStore.getStatusOnboardingUser()

    suspend fun saveStatusOnboarding(status: Boolean){
        appDataStore.saveStatusOnboardingUser(status)
    }

    companion object{
        private var INSTANCE: AppRepository? = null

        fun getInstance(appDataStore: AppDataStore ): AppRepository{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: AppRepository(appDataStore)
            }.also {
                INSTANCE = it
            }
        }
    }
}
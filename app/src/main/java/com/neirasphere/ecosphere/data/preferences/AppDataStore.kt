package com.neirasphere.ecosphere.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = "AppSetting")
class AppDataStore private constructor(private val appDataStore: DataStore<Preferences>){
    private val statusOnboarding = booleanPreferencesKey(STATUS_ONBOARDING_KEY)

    fun getStatusOnboardingUser(): Flow<Boolean> {
        return appDataStore.data.map {
            it[statusOnboarding] ?: false
        }
    }

    suspend fun saveStatusOnboardingUser(status: Boolean){
        appDataStore.edit {
            it[this.statusOnboarding] = status
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: AppDataStore? = null

        private const val STATUS_ONBOARDING_KEY = "statusOnboarding"

        fun getInstance(dataStore: DataStore<Preferences>): AppDataStore{
            return INSTANCE ?: synchronized(this){
                val instance = AppDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
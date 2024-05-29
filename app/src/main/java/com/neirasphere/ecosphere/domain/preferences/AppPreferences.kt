package com.neirasphere.ecosphere.domain.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.neirasphere.ecosphere.data.preferences.AppDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface AppPreferences {

    val statusOnboarding : Preferences.Key<Boolean>

    fun getStatusOnboardingUser(): Flow<Boolean>

    suspend fun saveStatusOnboardingUser(status: Boolean)

}
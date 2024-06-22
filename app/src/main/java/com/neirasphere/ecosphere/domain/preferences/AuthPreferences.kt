package com.neirasphere.ecosphere.domain.preferences

import androidx.datastore.preferences.core.Preferences
import com.neirasphere.ecosphere.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthPreferences {

    val statusLogin : Preferences.Key<Boolean>
    val tokenUser : Preferences.Key<String>
    val firstName : Preferences.Key<String>
    val lastName : Preferences.Key<String>
    val email : Preferences.Key<String>
    val avatar: Preferences.Key<String>

    fun isLoggedIn(): Flow<Boolean>

    fun getSession(): Flow<UserData>

    suspend fun saveSessionUser(user: UserData)

    suspend fun clearSessionUser()

    suspend fun setLoginStatus(isLogin: Boolean)
}
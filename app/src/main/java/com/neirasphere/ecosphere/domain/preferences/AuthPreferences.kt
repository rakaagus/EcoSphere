package com.neirasphere.ecosphere.domain.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AuthPreferences {

    val statusLogin : Preferences.Key<Boolean>
    val tokenUser : Preferences.Key<String>
    val userName : Preferences.Key<String>
    fun isLoggedIn(): Flow<Boolean>

    fun getToken(): Flow<String?>

    fun getUserName(): Flow<String?>

    suspend fun setLoginStatus(isLogin: Boolean)

    suspend fun saveToken(token: String)

    suspend fun saveUserName(userName: String)

    suspend fun deleteToken()

    suspend fun deleteUserName()
}
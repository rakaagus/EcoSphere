package com.neirasphere.ecosphere.domain.repository


import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.neirasphere.ecosphere.data.Result
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getStatusOnboardingUser(): Flow<Boolean>

    suspend fun saveStatusOnboardingUser(status: Boolean)

    fun isLoggedIn(): Flow<Boolean>

    fun getToken(): Flow<String?>

    fun getUserName(): Flow<String?>

    suspend fun setLoginStatus(isLogin: Boolean)

    suspend fun saveToken(token: String)

    suspend fun saveUserName(userName: String)

    suspend fun deleteToken()

    suspend fun deleteUserName()

    fun loginWithGoogle(credential: AuthCredential): Flow<Result<AuthResult>>
}
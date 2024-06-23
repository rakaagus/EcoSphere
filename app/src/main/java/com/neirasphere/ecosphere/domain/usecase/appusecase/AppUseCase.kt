package com.neirasphere.ecosphere.domain.usecase.appusecase

import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import com.neirasphere.ecosphere.data.remote.response.RegisterResponse
import com.neirasphere.ecosphere.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface AppUseCase {

    fun login(email: String, password: String): Flow<ResultDefault<LoginResponse>>

    fun register(firstName: String, lastName: String, email: String, password: String): Flow<ResultDefault<RegisterResponse>>

    suspend fun saveSessionUser(session: UserData)

    fun getSessionUser(): Flow<UserData>

    suspend fun clearSessionUser()

    fun isLoggedIn(): Flow<Boolean>

    suspend fun setLoginStatus(isLogin: Boolean)

    fun loginWithGoogle(credential: AuthCredential): Flow<Result<AuthResult>>

    fun loginWithFacebook(credential: AccessToken): Flow<Result<AuthResult>>

    fun getStatusOnboardingUser(): Flow<Boolean>

    suspend fun saveStatusOnboardingUser(status: Boolean)
}
package com.neirasphere.ecosphere.domain.usecase.appusecase

import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import com.neirasphere.ecosphere.data.remote.response.RegisterResponse
import com.neirasphere.ecosphere.domain.model.UserData
import com.neirasphere.ecosphere.domain.preferences.AppPreferences
import com.neirasphere.ecosphere.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUseCaseInteract @Inject constructor(
    private val appRepository: AppRepository
) : AppUseCase{
    override fun login(email: String, password: String): Flow<ResultDefault<LoginResponse>> = appRepository.login(email, password)

    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<ResultDefault<RegisterResponse>> = appRepository.register(firstName, lastName, email, password)

    override suspend fun saveSessionUser(session: UserData) {
        appRepository.saveSessionUser(session)
    }

    override fun getSessionUser(): Flow<UserData> = appRepository.getSessionUser()

    override suspend fun clearSessionUser() {
        appRepository.clearSessionUser()
    }

    override fun isLoggedIn(): Flow<Boolean> = appRepository.isLoggedIn()

    override suspend fun setLoginStatus(isLogin: Boolean) {
        appRepository.setLoginStatus(isLogin)
    }

    override fun loginWithGoogle(credential: AuthCredential): Flow<Result<AuthResult>> = appRepository.loginWithGoogle(credential)

    override fun loginWithFacebook(credential: AccessToken): Flow<Result<AuthResult>> = appRepository.loginWithFacebook(credential)

    override fun getStatusOnboardingUser(): Flow<Boolean> = appRepository.getStatusOnboardingUser()

    override suspend fun saveStatusOnboardingUser(status: Boolean) {
        appRepository.saveStatusOnboardingUser(status)
    }
}
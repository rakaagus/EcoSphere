package com.neirasphere.ecosphere.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.neirasphere.ecosphere.data.preferences.AppDataStore
import com.neirasphere.ecosphere.data.preferences.AuthDataStore
import com.neirasphere.ecosphere.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.neirasphere.ecosphere.data.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.tasks.await

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val appDataStore: AppDataStore,
    private val authDataStore: AuthDataStore,
    private val firebaseAuth: FirebaseAuth
) : AppRepository {
    override fun getStatusOnboardingUser(): Flow<Boolean> = appDataStore.getStatusOnboardingUser()

    override suspend fun saveStatusOnboardingUser(status: Boolean) {
        appDataStore.saveStatusOnboardingUser(status)
    }

    override fun isLoggedIn(): Flow<Boolean> = authDataStore.isLoggedIn()

    override fun getToken(): Flow<String?> = authDataStore.getToken()

    override fun getUserName(): Flow<String?> = authDataStore.getUserName()

    override suspend fun setLoginStatus(isLogin: Boolean) {
        authDataStore.setLoginStatus(isLogin)
    }

    override suspend fun saveToken(token: String) {
        authDataStore.saveToken(token)
    }

    override suspend fun saveUserName(userName: String) {
        authDataStore.saveUserName(userName)
    }

    override suspend fun deleteToken() = authDataStore.deleteToken()

    override suspend fun deleteUserName() = authDataStore.deleteUserName()
    override fun loginWithGoogle(credential: AuthCredential): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Result.Success(result))
        }.catch {
            emit(Result.Error(it.message.toString()))
        }
    }

}
package com.neirasphere.ecosphere.data.repository

import android.util.Log
import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.data.preferences.AppDataStore
import com.neirasphere.ecosphere.data.preferences.AuthDataStore
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.response.RegisterResponse
import com.neirasphere.ecosphere.domain.model.UserData
import com.neirasphere.ecosphere.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val appDataStore: AppDataStore,
    private val authDataStore: AuthDataStore,
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService,
) : AppRepository {
    override fun login(email: String, password: String) = flow {
        emit(ResultDefault.Loading)
        try {
            val response = apiService.login(email, password)
            val data = response.data
            val user = data?.user
            val success = data?.success ?: false
            if (success) {
                setLoginStatus(true)
                val userData = UserData(
                    id = user?.idUser,
                    token = data?.token ?: "",
                    firstName = user?.namaDepan,
                    lastName = user?.namaBelakang,
                    email = user?.email,
                    avatar = user?.imgProfile
                )
                saveSessionUser(userData)
                Log.e("AppRepository", "data Login ${authDataStore.isLoggedIn()}")
                Log.e("AppRepository", "data Login ${getSessionUser()}")

                // Logging user data
                Log.i("AppRepository", "User ID: ${userData.id}")
                Log.i("AppRepository", "User Token: ${userData.token}")
                Log.i("AppRepository", "User First Name: ${userData.firstName}")
                Log.i("AppRepository", "User Last Name: ${userData.lastName}")
                Log.i("AppRepository", "User Email: ${userData.email}")
                Log.i("AppRepository", "User Avatar: ${userData.avatar}")
            }else {
                emit(ResultDefault.Error(response.message))
            }
            emit(ResultDefault.Success(response))
        } catch (e: Exception) {
            Log.d("AppRepository", "loginUser: ${e.message.toString()}")
            emit(ResultDefault.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<ResultDefault<RegisterResponse>> = flow {
        emit(ResultDefault.Loading)
        try {
            val response = apiService.register(firstName, lastName, email, password)
            val data = response.data
            if(response.success){
                emit(ResultDefault.Success(response))
            }else {
                emit(ResultDefault.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("AppRepository", "registerUser: ${e.message.toString()}")
            emit(ResultDefault.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    override fun getStatusOnboardingUser(): Flow<Boolean> = appDataStore.getStatusOnboardingUser()

    override suspend fun saveStatusOnboardingUser(status: Boolean) {
        appDataStore.saveStatusOnboardingUser(status)
    }

    override suspend fun saveSessionUser(session: UserData) {
        authDataStore.saveSessionUser(session)
    }

    override fun getSessionUser(): Flow<UserData> = authDataStore.getSession()

    override suspend fun clearSessionUser() {
        authDataStore.clearSessionUser()
    }

    override fun isLoggedIn(): Flow<Boolean> = authDataStore.isLoggedIn()


    override suspend fun setLoginStatus(isLogin: Boolean) {
        authDataStore.setLoginStatus(isLogin)
    }

    override fun loginWithGoogle(credential: AuthCredential): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Result.Success(result))
        }.catch {
            emit(Result.Error(it.message.toString()))
        }
    }

    override fun loginWithFacebook(credential: AccessToken): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading())
            try {
                val credential = FacebookAuthProvider.getCredential(credential.token)
                val result = firebaseAuth.signInWithCredential(credential).await()
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

}
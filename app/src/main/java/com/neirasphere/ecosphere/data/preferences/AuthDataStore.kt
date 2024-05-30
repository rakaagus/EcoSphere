package com.neirasphere.ecosphere.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.neirasphere.ecosphere.domain.preferences.AuthPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataStore @Inject constructor(
    private val authDataStore: DataStore<Preferences>
) : AuthPreferences{
    override val statusLogin = booleanPreferencesKey(STATUS_LOGIN_KEY)
    override val tokenUser = stringPreferencesKey(TOKEN_USER_KEY)
    override val userName = stringPreferencesKey(USER_NAME_KEY)

    override fun isLoggedIn(): Flow<Boolean> = authDataStore.data.map {
        it[statusLogin] ?: false
    }
    override fun getToken(): Flow<String?> = authDataStore.data.map {
        it[tokenUser]
    }

    override fun getUserName(): Flow<String?> = authDataStore.data.map {
        it[userName]
    }

    override suspend fun setLoginStatus(isLogin: Boolean) {
        authDataStore.edit {
            it[statusLogin] = isLogin
        }
    }

    override suspend fun saveToken(token: String) {
        authDataStore.edit {
            it[tokenUser] = token
        }
    }

    override suspend fun saveUserName(userName: String) {
        authDataStore.edit {
            it[this.userName] = userName
        }
    }

    override suspend fun deleteToken() {
        authDataStore.edit {
            it.remove(tokenUser)
        }
    }

    override suspend fun deleteUserName() {
        authDataStore.edit {
            it.remove(userName)
        }
    }

    companion object{
        private const val STATUS_LOGIN_KEY = "status_login_key"
        private const val TOKEN_USER_KEY = "token_user_key"
        private const val USER_NAME_KEY = "user_name_key"
    }
}
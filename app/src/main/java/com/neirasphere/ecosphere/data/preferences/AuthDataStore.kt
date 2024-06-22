package com.neirasphere.ecosphere.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.neirasphere.ecosphere.domain.model.UserData
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
    override val firstName = stringPreferencesKey(FIRST_NAME_KEY)
    override val lastName = stringPreferencesKey(LAST_NAME_KEY)
    override val email = stringPreferencesKey(EMAIL_USER_KEY)
    override val avatar = stringPreferencesKey(AVATAR_USER_KEY)

    override fun isLoggedIn(): Flow<Boolean> = authDataStore.data.map {
        it[statusLogin] ?: false
    }

    override fun getSession(): Flow<UserData> {
        return authDataStore.data.map {
            UserData(
                token = it[tokenUser] ?: "",
                firstName = it[firstName] ?: "",
                lastName = it[lastName] ?: "",
                email = it[email] ?: "",
                avatar = it[avatar] ?: ""
            )
        }
    }

    override suspend fun saveSessionUser(user: UserData) {
        authDataStore.edit {
            it[tokenUser] = user.token ?: ""
            it[lastName] = user.lastName ?: ""
            it[firstName] = user.firstName ?: ""
            it[email] = user.email ?: ""
            it[avatar] = user.avatar ?: ""
        }
    }

    override suspend fun clearSessionUser() {
        authDataStore.edit {
            it.clear()
        }
        setLoginStatus(false)
    }

    override suspend fun setLoginStatus(isLogin: Boolean) {
        authDataStore.edit {
            it[statusLogin] = isLogin
        }
    }

    companion object{
        private const val STATUS_LOGIN_KEY = "status_login_key"
        private const val TOKEN_USER_KEY = "token_user_key"
        private const val FIRST_NAME_KEY = "first_name_key"
        private const val LAST_NAME_KEY = "last_name_key"
        private const val EMAIL_USER_KEY = "email_user_key"
        private const val AVATAR_USER_KEY = "avatar_user_key"
    }
}
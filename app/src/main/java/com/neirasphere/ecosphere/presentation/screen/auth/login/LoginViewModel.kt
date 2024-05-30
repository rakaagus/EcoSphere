package com.neirasphere.ecosphere.presentation.screen.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.presentation.screen.auth.common.LoginGoogleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private val _stateGoogle = mutableStateOf(LoginGoogleState())
    val stateGoogle: State<LoginGoogleState> = _stateGoogle

    fun loginWithGoogle(credential: AuthCredential){
        viewModelScope.launch {
            repository.loginWithGoogle(credential).collect{result ->
                when(result){
                    is Result.Loading -> {
                        _stateGoogle.value = LoginGoogleState(loading = true)
                    }
                    is Result.Success -> {
                        _stateGoogle.value = LoginGoogleState(success = result.data)
                    }
                    is Result.Error -> {
                        _stateGoogle.value = LoginGoogleState(error = result.message)
                    }
                }
            }
        }
    }

}
package com.neirasphere.ecosphere.presentation.screen.auth.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.presentation.screen.auth.common.LoginFirebaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    private val _stateGoogle = mutableStateOf(LoginFirebaseState())
    val stateGoogle: State<LoginFirebaseState> = _stateGoogle

    private val _stateFacebook = mutableStateOf(LoginFirebaseState())
    val stateFacebook: State<LoginFirebaseState> = _stateFacebook

    fun loginWithGoogle(credential: AuthCredential){
        viewModelScope.launch {
            repository.loginWithGoogle(credential).collect{result ->
                when(result){
                    is Result.Loading -> {
                        _stateGoogle.value = LoginFirebaseState(loading = true)
                    }
                    is Result.Success -> {
                        _stateGoogle.value = LoginFirebaseState(success = result.data)
                    }
                    is Result.Error -> {
                        _stateGoogle.value = LoginFirebaseState(error = result.message)
                    }
                }
            }
        }
    }

    fun loginWithFacebook(credential: AccessToken){
        viewModelScope.launch {
            repository.loginWithFacebook(credential).collect{result ->
                when(result){
                    is Result.Loading -> {
                        _stateGoogle.value = LoginFirebaseState(loading = true)
                    }
                    is Result.Success -> {
                        _stateGoogle.value = LoginFirebaseState(success = result.data)
                    }
                    is Result.Error -> {
                        _stateGoogle.value = LoginFirebaseState(error = result.message)
                    }
                }
            }
        }
    }
}
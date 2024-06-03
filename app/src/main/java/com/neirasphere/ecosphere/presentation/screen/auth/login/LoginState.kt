package com.neirasphere.ecosphere.presentation.screen.auth.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isConnectLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = null
)

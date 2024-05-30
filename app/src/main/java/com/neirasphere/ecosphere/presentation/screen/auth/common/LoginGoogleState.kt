package com.neirasphere.ecosphere.presentation.screen.auth.common

import com.google.firebase.auth.AuthResult

data class LoginGoogleState(
    val success: AuthResult? = null,
    val error: String? = null,
    val loading: Boolean = true
)
package com.neirasphere.ecosphere.presentation.screen.profile.edit

import com.neirasphere.ecosphere.domain.model.UserData

data class EditProfileState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val birtDay: String = "",
    val location: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = null,
    val user: UserData? = null
)

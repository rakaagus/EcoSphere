package com.neirasphere.ecosphere.presentation.screen.community

data class PostState(
    val content: String = "",
    val isLoading: Boolean = false,
    val isConnectLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = null
)
package com.neirasphere.ecosphere.presentation.screen.community

import com.neirasphere.ecosphere.data.remote.response.DataItem

data class PostState(
    val content: String = "",
    val isLoading: Boolean = false,
    val isConnectLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = null
)

data class GetPostsState(
    val posts: List<DataItem?>? = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null,
    val isError: String? = null
)
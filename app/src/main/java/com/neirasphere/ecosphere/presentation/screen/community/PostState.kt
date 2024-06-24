package com.neirasphere.ecosphere.presentation.screen.community

import com.neirasphere.ecosphere.domain.model.CommunityPostSQL
import com.neirasphere.ecosphere.domain.model.PostComment

data class PostState(
    val content: String = "",
    val isLoading: Boolean = false,
    val isConnectLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = null
)

data class GetPostsState(
    val posts: List<CommunityPostSQL> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null,
    val isError: String? = null
)

data class GetPostCommentState(
    val comments: List<PostComment> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null,
    val isError: String? = null
)
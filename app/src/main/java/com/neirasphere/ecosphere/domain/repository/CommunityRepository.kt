package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.data.remote.response.LikeItem
import com.neirasphere.ecosphere.data.remote.response.PostResponse
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.model.CommunityPostSQL
import com.neirasphere.ecosphere.domain.model.PostComment
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface CommunityRepository {

    val communityPost : MutableList<CommunityPost>

    fun getAllCommunityPost() : Flow<CommunityResult<MutableList<CommunityPostSQL>>>

    fun getPostById(id: Int) : Flow<CommunityResult<MutableList<CommunityPostSQL>>>

    fun getCommunityLikes(id: Int) : Flow<CommunityResult<MutableList<LikeItem>>>

    fun getCommentsByPostId(id: Int) : Flow<CommunityResult<MutableList<PostComment>>>

    fun postWithImage(token: String, post: String, postImg: MultipartBody.Part): Flow<CommunityResult<CommunityPostResponse>>

    fun post(token: String, post: String): Flow<CommunityResult<CommunityPostResponse>>

    fun postLike(token: String, id: Int, liked: Boolean): Flow<CommunityResult<PostResponse>>

    fun postComment(token: String, id: Int, comment: String): Flow<CommunityResult<PostResponse>>
}
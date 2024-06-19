package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.model.CommunityPostSQL
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface CommunityRepository {

    val communityPost : MutableList<CommunityPost>

    fun getAllCommunityPost() : Flow<CommunityResult<MutableList<CommunityPostSQL>>>

    fun postWithImage(post: String, postImg: MultipartBody.Part): Flow<CommunityResult<CommunityPostResponse>>

    fun post(post: String): Flow<CommunityResult<CommunityPostResponse>>
}
package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.remote.response.DataItem
import com.neirasphere.ecosphere.domain.model.CommunityPost
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    val communityPost : MutableList<CommunityPost>

    fun getAllCommunityPost(token: String) : Flow<CommunityResult<List<DataItem?>?>>

//    fun post(post: String, createdAt: String, postImg: MultipartBody.Part): Flow<Result<CommunityPostResponse>>
}
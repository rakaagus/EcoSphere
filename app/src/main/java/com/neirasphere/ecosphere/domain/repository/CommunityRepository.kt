package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.domain.model.CommunityPost
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Call

interface CommunityRepository {

    val communityPost : MutableList<CommunityPost>

    fun getAllCommunityPost() : Flow<List<CommunityPost>>

    fun post(post: String, createdAt: String, postImg: MultipartBody.Part): Call<CommunityPostResponse>
}
package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val apiService: ApiService
) : CommunityRepository{

    override val communityPost = mutableListOf<CommunityPost>()

    init {
        if (communityPost.isEmpty()) {
            dataSource.communityPostData().forEach{
                communityPost.add(it)
            }
        }
    }

    override fun getAllCommunityPost(): Flow<List<CommunityPost>> = flowOf(communityPost)

    override fun post(
        post: String,
        createdAt: String,
        postImg: MultipartBody.Part
    ): Call<CommunityPostResponse> {
        return apiService.post(post, createdAt, postImg)
    }

}
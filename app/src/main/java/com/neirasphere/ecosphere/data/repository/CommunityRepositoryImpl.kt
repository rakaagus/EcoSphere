package com.neirasphere.ecosphere.data.repository

import android.util.Log
import com.neirasphere.ecosphere.ResultDefault
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
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

    override fun post(content: String) = flow {
        emit(ResultDefault.Loading)
        try {
            val response = apiService.post(content)
            val data = response.data
            val success = data.success
            if (success) {}
            emit(ResultDefault.Success(response))
        } catch (e: Exception) {
            Log.d("CommunityRepository", "communityPost: ${e.message.toString()}")
            emit(ResultDefault.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}
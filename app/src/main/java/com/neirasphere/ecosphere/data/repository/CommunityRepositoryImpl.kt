package com.neirasphere.ecosphere.data.repository

import android.graphics.BitmapFactory
import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.RemoteDataSource
import com.neirasphere.ecosphere.data.remote.response.DataItem
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Singleton
class CommunityRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val remoteDataSource: RemoteDataSource,
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

    @OptIn(ExperimentalEncodingApi::class)
    override fun getAllCommunityPost(token: String): Flow<CommunityResult<List<DataItem?>?>> = flow {
        emit(CommunityResult.Loading())
        try {
            val postDataStore = mutableListOf<CommunityPost>()
            val response = remoteDataSource.getAllCommunityPosts(token)
            val result = response.data
            if (result != null) {
                result.forEach {
                    val post = it?.post
                    val id = it?.communityId
                    val userId = it?.idUser
                    val postImgBuffer = it?.postImg?.data
                    if (postImgBuffer != null && postImgBuffer.isNotEmpty()) {
                        val imageDataByteArray = ByteArray(postImgBuffer.size)
                        for (i in postImgBuffer.indices) {
                            imageDataByteArray[i] = postImgBuffer[i]?.toByte() ?:0
                        }
                        val imageData = Base64.decode(imageDataByteArray)
                        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
                    }
                }
            }
            emit(CommunityResult.Success(result))
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

//    override fun post(
//        post: String,
//        createdAt: String,
//        postImg: MultipartBody.Part
//    ): CommunityPostResponse {
//        return apiService.post(post, createdAt, postImg)
//    }

}
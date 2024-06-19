package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.preferences.AuthDataStore
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.RemoteDataSource
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.model.CommunityPostSQL
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.io.encoding.ExperimentalEncodingApi

@Singleton
class CommunityRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val remoteDataSource: RemoteDataSource,
    private val apiService: ApiService,
    private val authDataStore: AuthDataStore
) : CommunityRepository{

    override val communityPost = mutableListOf<CommunityPost>()

    val token = authDataStore.getToken().toString()

    init {
        if (communityPost.isEmpty()) {
            dataSource.communityPostData().forEach{
                communityPost.add(it)
            }
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    override fun getAllCommunityPost(): Flow<CommunityResult<MutableList<CommunityPostSQL>>> = flow {
        emit(CommunityResult.Loading())
        try {
            val postDataStore = mutableListOf<CommunityPostSQL>()
            val response = remoteDataSource.getAllCommunityPosts(token)
            val result = response.data
            if (result != null) {
                result.forEach {
                    val postImg = it?.postImg
                    val createdAt = it?.createdAt
                    val post = it?.post
                    val idUser = it?.idUser
                    val communityId = it?.communityId
                    val email = it?.email
                    val imgProfile = it?.imgProfile
                    val resultData = CommunityPostSQL(
                        id = communityId!!,
                        user = DataSource.communityPostUser()[0],
                        text = post!!,
                        image = postImg!!,
                        comments = 1000,
                        likes = 2000,
                        liked = false,
                        views = 2000,
                        createdAt = createdAt!!
                    )
                    postDataStore.add(resultData)
                }
            }
            emit(CommunityResult.Success(postDataStore))
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun postWithImage(post: String, postImg: MultipartBody.Part) = flow {
        emit(CommunityResult.Loading())
        try {
            val response = apiService.postWithImage(token, post, postImg)
            val data = response.message
            if (data.contains("success")) {
                emit(CommunityResult.Success(response))
            }
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun post(post: String) = flow {
        emit(CommunityResult.Loading())
        try {
            val response = apiService.post(token, post)
            val data = response.message
            if (data.contains("success")) {
                emit(CommunityResult.Success(response))
            }
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}
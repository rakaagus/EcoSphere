package com.neirasphere.ecosphere.data.repository

import android.util.Log
import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.preferences.AuthDataStore
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.RemoteDataSource
import com.neirasphere.ecosphere.data.remote.response.CommentItem
import com.neirasphere.ecosphere.data.remote.response.LikeItem
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

    override fun getCommunityLikes(id: Int): Flow<CommunityResult<MutableList<LikeItem>>> = flow {
        emit(CommunityResult.Loading())
        try {
            val likeDataStore = mutableListOf<LikeItem>()
            val response = remoteDataSource.getCommunityLikes(id)
            val result = response.data
            val success = response.success
            if (success == true) {
                result?.forEach {
                    if (it != null) {
                        likeDataStore.add(it)
                    }
                }
            }
            emit(CommunityResult.Success(likeDataStore))
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllCommunityPost(): Flow<CommunityResult<MutableList<CommunityPostSQL>>> = flow {
        emit(CommunityResult.Loading())
        try {
            val postDataStore = mutableListOf<CommunityPostSQL>()
            val response = remoteDataSource.getAllCommunityPosts()
            val result = response.data
            result?.forEach {
                val postImg = it?.postImg
                val createdAt = it?.createdAt
                val post = it?.post
                val idUser = it?.idUser
                val communityId = it?.communityId
                val email = it?.email
                val imgProfile = it?.imgProfile
                var likes = 0
                try {
                    val likeResponse = remoteDataSource.getCommunityLikes(communityId!!)
                    val likeResult = likeResponse.data
                    likes = likeResult?.size ?: 0
                } catch (e: Exception) {
                    if (e.message!!.contains("404")) {
                        likes = 0
                    }
                }
                val resultData = CommunityPostSQL(
                    id = communityId!!,
                    user = DataSource.communityPostUser()[0],
                    text = post!!,
                    image = postImg,
                    comments = 1000,
                    likes = likes,
                    liked = false,
                    views = 2000,
                    createdAt = createdAt!!
                )
                postDataStore.add(resultData)
                Log.d("cek repo impl", "getAllCommunityPost: $postDataStore")
            }
            Log.d("cek before emit", "getAllCommunityPost: $postDataStore")
            emit(CommunityResult.Success(postDataStore))
        } catch (e: Exception) {
            Log.d("cek error", "getAllCommunityPost: ${e.message}")
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAComment(id: Int): Flow<CommunityResult<MutableList<CommentItem>>> = flow {
        emit(CommunityResult.Loading())
        try {
            val commentDataStore = mutableListOf<CommentItem>()
            val response = remoteDataSource.getAComment(id)
            val result = response.data
            val success = response.success
            if (success == true) {
                result?.forEach {
                    if (it != null) {
                        commentDataStore.add(it)
                    }
                }
            }
            emit(CommunityResult.Success(commentDataStore))
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }

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
package com.neirasphere.ecosphere.data.repository

import android.util.Log
import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.preferences.AuthDataStore
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.RemoteDataSource
import com.neirasphere.ecosphere.data.remote.response.LikeItem
import com.neirasphere.ecosphere.data.remote.response.PostResponse
import com.neirasphere.ecosphere.domain.model.CommunityPost
import com.neirasphere.ecosphere.domain.model.CommunityPostSQL
import com.neirasphere.ecosphere.domain.model.PostComment
import com.neirasphere.ecosphere.domain.model.User
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
                val postImg = if (it?.postImg != null) remoteDataSource.imageBaseURL + it.postImg else null
                val createdAt = it?.createdAt
                val post = it?.post
                val idUser = it?.idUser
                val communityId = it?.communityId
                val email = it?.email
                val imgProfile = it?.imgProfile
                val userResponse = remoteDataSource.getUserById(idUser!!)
                val userResult = userResponse.data
                val user = User(
                    id = userResult.idUser,
                    namaDepan = userResult.namaDepan,
                    namaBelakang = userResult.namaBelakang,
                    email = userResult.email,
                    avatar = if (userResult.imgProfile != null) remoteDataSource.imageBaseURL + userResult.imgProfile else null
                )
                var likes = 0
                var liked = false
                try {
                    val likeResponse = remoteDataSource.getCommunityLikes(communityId!!)
                    val likeResult = likeResponse.data
                    likes = likeResult?.size ?: 0
//                    liked = likeResult?.any { it?.idUser == userId } ?: false
                } catch (e: Exception) {
                    if (e.message!!.contains("404")) {
                        likes = 0
                    }
                }
                var comments = 0
                try {
                    val commentResponse = remoteDataSource.getCommentsByPostId(communityId!!)
                    val commentResult = commentResponse.data
                    comments = commentResult?.size ?: 0
                } catch (e: Exception) {
                    Log.d("error fetch comment impl", "${e.message}")
                    comments = 0
                }
                val resultData = CommunityPostSQL(
                    id = communityId!!,
                    user = user,
                    text = post!!,
                    image = postImg,
                    comments = comments,
                    likes = likes,
                    liked = liked,
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

    override fun getPostById(id: Int): Flow<CommunityResult<MutableList<CommunityPostSQL>>> = flow {
        emit(CommunityResult.Loading())
        try {
            val postDataStore = mutableListOf<CommunityPostSQL>()
            val response = remoteDataSource.getPostById(id)
            val result = response.data
            result?.forEach {
                val postImg = if (it?.postImg != null) remoteDataSource.imageBaseURL + it.postImg else null
                val createdAt = it?.createdAt
                val post = it?.post
                val idUser = it?.idUser
                val communityId = it?.communityId
                val email = it?.email
                val imgProfile = it?.imgProfile
                val userResponse = remoteDataSource.getUserById(idUser!!)
                val userResult = userResponse.data
                val user = User(
                    id = userResult.idUser,
                    namaDepan = userResult.namaDepan,
                    namaBelakang = userResult.namaBelakang,
                    email = userResult.email,
                    avatar = if (userResult.imgProfile != null) remoteDataSource.imageBaseURL + userResult.imgProfile else null
                )
                var likes = 0
                var liked = false
                try {
                    val likeResponse = remoteDataSource.getCommunityLikes(communityId!!)
                    val likeResult = likeResponse.data
                    likes = likeResult?.size ?: 0
//                    liked = likeResult?.any { it?.idUser == userId } ?: false
                } catch (e: Exception) {
                    if (e.message!!.contains("404")) {
                        likes = 0
                    }
                }
                var comments = 0
                try {
                    val commentResponse = remoteDataSource.getCommentsByPostId(communityId!!)
                    val commentResult = commentResponse.data
                    comments = commentResult?.size ?: 0
                } catch (e: Exception) {
                    Log.d("error fetch comment impl", "${e.message}")
                    comments = 0
                }
                val resultData = CommunityPostSQL(
                    id = communityId!!,
                    user = user,
                    text = post!!,
                    image = postImg,
                    comments = comments,
                    likes = likes,
                    liked = liked,
                    views = 2000,
                    createdAt = createdAt!!
                )
                postDataStore.add(resultData)
                Log.d("cek repo impl by id", "getAllCommunityPost: $postDataStore")
            }
            Log.d("cek before emit by id", "getAllCommunityPost: $postDataStore")
            emit(CommunityResult.Success(postDataStore))
        } catch (e: Exception) {
            Log.d("cek error", "getAllCommunityPost: ${e.message}")
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCommentsByPostId(id: Int): Flow<CommunityResult<MutableList<PostComment>>> = flow {
        emit(CommunityResult.Loading())
        try {
            val commentDataStore = mutableListOf<PostComment>()
            val response = remoteDataSource.getCommentsByPostId(id)
            val result = response.data
            val success = response.success
            if (success == true) {
                result?.forEach {
                    var user : User
                    val idUser = it?.idUser
                    try {
                        val userResponse = remoteDataSource.getUserById(idUser!!)
                        val userResult = userResponse.data
                        val userSuccess = userResponse.success
                        user = User(
                            id = userResult.idUser,
                            namaDepan = userResult.namaDepan,
                            namaBelakang = userResult.namaBelakang,
                            email = userResult.email,
                            avatar = if (userResult.imgProfile != null) remoteDataSource.imageBaseURL + userResult.imgProfile else null
                        )
                    } catch (e: Exception) {
                        user = DataSource.communityPostUser()[0]
                    }
                    val resultData = PostComment(
                        id = it?.commentId!!,
                        PostId = it.communityId!!,
                        UserId = it.idUser!!,
                        User = user,
                        commentImg = it.commentImg,
                        createdAt = it.createdAt!!,
                        comment = it.comment!!,
                        email = it.email
                    )
                    commentDataStore.add(resultData)
                }
            }
            Log.d("cek komen sukses", "masuk")
            emit(CommunityResult.Success(commentDataStore))
        } catch (e: Exception) {
            Log.d("cek error getComment", "getAllCommunityPost: ${e.message}")
            emit(CommunityResult.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun postWithImage(token: String, post: String, postImg: MultipartBody.Part) = flow {
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

    override fun post(token: String, post: String) = flow {
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

    override fun postLike(token: String, id: Int, liked: Boolean): Flow<CommunityResult<PostResponse>> = flow {
        emit(CommunityResult.Loading())
        try {
            val response = if (liked) apiService.postUnlike(token, id) else apiService.postLike(token, id)
            val success = response.success
            if (success == true) {
                emit(CommunityResult.Success(response))
            }
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun postComment(
        token: String,
        id: Int,
        comment: String
    ): Flow<CommunityResult<PostResponse>> = flow {
        emit(CommunityResult.Loading())
        try {
            val response = apiService.postComment(token, id, comment)
            val success = response.success
            if (success == true) {
                emit(CommunityResult.Success(response))
            }
        } catch (e: Exception) {
            emit(CommunityResult.Error(e.message.toString()))

        }
    }

}
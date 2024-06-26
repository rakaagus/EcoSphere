package com.neirasphere.ecosphere.presentation.screen.community

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import com.neirasphere.ecosphere.presentation.screen.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val repository: CommunityRepository,
    private val appRepository: AppRepository
): ViewModel() {

    private val _postState = MutableStateFlow(PostState())
    val postState = _postState.asStateFlow()

    private val _getPostsState = MutableStateFlow(GetPostsState())
    val getPostsState = _getPostsState.asStateFlow()

    private val _getPostState = MutableStateFlow(GetPostsState())
    val getPostState = _getPostState.asStateFlow()

    private val  _getPostCommentState = MutableStateFlow(GetPostCommentState())
    val getPostCommentState = _getPostCommentState.asStateFlow()

    private val _likeState = MutableStateFlow(PostState())
    val likeState = _likeState.asStateFlow()

    private val _user = MutableStateFlow(ProfileState())
    val user = _user.asStateFlow()

    fun getUser() = viewModelScope.launch {
        appRepository.getSessionUser().collect{ result ->
            _user.update {
                it.copy(
                    user = result
                )
            }
        }
    }

    private var postId = 0

    fun setPostId(id: Int) {
        postId = id
    }

    fun getPostId(): Int {
        return postId
    }

    init {
        getAllCommunityPosts()
    }

    private fun getAllCommunityPosts() = viewModelScope.launch {
//        val userId = user.value.user?.id
        repository.getAllCommunityPost().collect { result ->
            when (result) {
                is CommunityResult.Error -> _getPostsState.update {
                    it.copy(
                        isLoading = false,
                        isError = result.message
                    )
                }

                is CommunityResult.Loading -> _getPostsState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                is CommunityResult.Success -> _getPostsState.update {
                    it.copy(
                        isLoading = false,
                        posts = result.data
                    )
                }
            }
        }
    }

    fun getPostById() = viewModelScope.launch {
//        val userId = user.value.user?.id
        repository.getPostById(postId).collect { result ->
            when (result) {
                is CommunityResult.Error -> _getPostState.update {
                    Log.d("cek error get post by id", "${result.message}")
                    it.copy(
                        isLoading = false,
                        isError = result.message
                    )
                }

                is CommunityResult.Loading -> _getPostState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                is CommunityResult.Success -> _getPostState.update {
                    Log.d("cek sukses get post by id", "${result.data}")
                    it.copy(
                        isLoading = false,
                        posts = result.data
                    )
                }
            }
        }
    }

    fun getCommentsByPostId() = viewModelScope.launch {
        repository.getCommentsByPostId(postId).collect { result ->
            when (result) {
                is CommunityResult.Error -> _getPostCommentState.update {
                    it.copy(
                        isLoading = false,
                        isError = result.message
                    )
                }
                is CommunityResult.Loading -> _getPostCommentState.update {
                    it.copy(
                        isLoading = true
                    )
                }
                is CommunityResult.Success -> _getPostCommentState.update {
                    it.copy(
                        isLoading = false,
                        isError = "sukses",
                        comments = result.data
                    )
                }
            }
        }
    }

    fun postWithImage(token: String, post: String, img: File) = viewModelScope.launch {
        Log.d("file extension", "extension: ${img.extension}")
        val requestFile = img.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("post_img", img.name, requestFile)
        Log.d("body extension", "extension: ${body.body.contentType()}")

        repository.postWithImage(
            token = "Bearer ${token}",
            post,
            body
        ).collect{ result ->
            when (result) {
                is CommunityResult.Loading -> {
                    _postState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is CommunityResult.Success -> {
                    _postState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
                is CommunityResult.Error -> {
                    _postState.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
            }
        }
    }

    fun post(token: String, post: String) = viewModelScope.launch {
        repository.post(
            token = "Bearer ${token}",
            post
        ).collect{ result ->
            when (result) {
                is CommunityResult.Loading -> {
                    _postState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is CommunityResult.Success -> {
                    _postState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
                is CommunityResult.Error -> {
                    _postState.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
            }
        }
    }

    fun postLike(token: String, postId: Int, liked: Boolean) = viewModelScope.launch {
        repository.postLike(token, postId, liked).collect { result ->
            when (result) {
                is CommunityResult.Loading -> {
                    _likeState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is CommunityResult.Success -> {
                    _likeState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
                is CommunityResult.Error -> {
                    _likeState.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
            }
        }
    }

    fun postComment(token: String, id: Int, comment: String) = viewModelScope.launch {
        repository.postComment(
            token = "Bearer ${token}",
            id,
            comment
        ).collect{ result ->
            when (result) {
                is CommunityResult.Loading -> {
                    _postState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is CommunityResult.Success -> {
                    _postState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
                is CommunityResult.Error -> {
                    _postState.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
            }
        }
    }

}

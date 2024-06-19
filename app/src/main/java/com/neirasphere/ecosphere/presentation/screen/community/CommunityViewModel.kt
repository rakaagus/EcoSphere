package com.neirasphere.ecosphere.presentation.screen.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
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

    init {
        getAllCommunityPosts()
    }

    private fun getAllCommunityPosts() = viewModelScope.launch {
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

    fun postWithImage(post: String, img: File) = viewModelScope.launch {
        val requestFile = img.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("post_img", img.name, requestFile)

        repository.postWithImage(
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

    fun post(post: String) = viewModelScope.launch {

        repository.post(
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

}

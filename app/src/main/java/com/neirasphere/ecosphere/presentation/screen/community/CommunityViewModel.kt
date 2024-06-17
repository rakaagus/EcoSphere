package com.neirasphere.ecosphere.presentation.screen.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neirasphere.ecosphere.data.CommunityResult
import com.neirasphere.ecosphere.domain.repository.AppRepository
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommunityViewModel @Inject constructor(
    private val repository: CommunityRepository,
    private val appRepository: AppRepository
): ViewModel() {

    private val _postState = MutableStateFlow(PostState())
    val postState = _postState.asStateFlow()

    private val _getPostsState = MutableStateFlow(GetPostsState())
    val getPostsState = _getPostsState.asStateFlow()

    init {
        getAllCommunityPosts(appRepository.getToken().toString())
    }

    private fun getAllCommunityPosts(token: String) = viewModelScope.launch {
        repository.getAllCommunityPost(token).collect { result ->
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

//    fun post(post: String, img: File) {
//        _postState.value = PostState(isLoading = true)
//
//        val requestFile = img.asRequestBody("image/*".toMediaTypeOrNull())
//        val body = MultipartBody.Part.createFormData("post_img", img.name, requestFile)
//
//        repository.post(
//            post,
//            "00-00-0000 00:00:00",
//            body
//        ).enqueue(object :
//            retrofit2.Callback<CommunityPostResponse> {
//            override fun onResponse(
//                call: Call<CommunityPostResponse>,
//                response: Response<CommunityPostResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _postState.value = PostState(isSuccess = true)
//                } else {
//                    _postState.value = PostState(isError = "Post failed: ${response.errorBody()?.string()}")
//                }
//            }
//
//            override fun onFailure(call: Call<CommunityPostResponse>, t: Throwable) {
//                _postState.value = PostState(isError = t.message)
//            }
//            }
//        )
//
//    }

}

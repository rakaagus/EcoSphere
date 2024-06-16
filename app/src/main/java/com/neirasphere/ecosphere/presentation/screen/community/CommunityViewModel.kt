package com.neirasphere.ecosphere.presentation.screen.community

import androidx.lifecycle.ViewModel
import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class CommunityViewModel @Inject constructor(
    private val repository: CommunityRepository
): ViewModel() {

    private val _postState = MutableStateFlow(PostState())
    val postState = _postState.asStateFlow()

    fun post(post: String, img: File) {
        _postState.value = PostState(isLoading = true)

        val requestFile = img.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("post_img", img.name, requestFile)

        repository.post(
            post,
            "00-00-0000 00:00:00",
            body
        ).enqueue(object :
            retrofit2.Callback<CommunityPostResponse> {
            override fun onResponse(
                call: Call<CommunityPostResponse>,
                response: Response<CommunityPostResponse>
            ) {
                if (response.isSuccessful) {
                    _postState.value = PostState(isSuccess = true)
                } else {
                    _postState.value = PostState(isError = "Post failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CommunityPostResponse>, t: Throwable) {
                _postState.value = PostState(isError = t.message)
            }
            }
        )

    }

}

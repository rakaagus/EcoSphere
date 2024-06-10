package com.neirasphere.ecosphere.presentation.screen.classification.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neirasphere.ecosphere.data.Result
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import com.neirasphere.ecosphere.data.repository.ClassificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClassificationResultViewModel @Inject constructor(private val classificationRepository: ClassificationRepository) : ViewModel() {

    private val _result = MutableLiveData<Result<ClassifyResult>>()
    val result: LiveData<Result<ClassifyResult>> = _result

    fun classifyTrash(file: File) {
        _result.value = Result.Loading()

        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        classificationRepository.classifyTrash(body).enqueue(object :
            Callback<ClassifyResult> {
            override fun onResponse(call: Call<ClassifyResult>, response: Response<ClassifyResult>) {
                if (response.isSuccessful) {
                    _result.value = Result.Success(response.body()!!)
                } else {
                    _result.value = Result.Error("Upload failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ClassifyResult>, t: Throwable) {
                _result.value = Result.Error("Upload failed: ${t.message}")
            }
        })
    }
}
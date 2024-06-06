package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.remote.ApiClassifyService
import com.neirasphere.ecosphere.data.remote.ApiService
import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject

class ClassificationRepository @Inject constructor(private val apiClassifyService: ApiClassifyService) {

    fun classifyTrash(file: MultipartBody.Part): Call<ClassifyResult> {
        return apiClassifyService.classifyTrash(file)
    }
}
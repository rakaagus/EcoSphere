package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiClassifyService {

    @Multipart
    @POST("/result")
    suspend fun classifyTrash(
        @Part file: MultipartBody.Part,
    ) : ClassifyResult

}
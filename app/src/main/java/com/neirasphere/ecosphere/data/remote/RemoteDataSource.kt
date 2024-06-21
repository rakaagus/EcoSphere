package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.ClassifyResult
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiClassifyService: ApiClassifyService,
    private val apiService: ApiService
){
    suspend fun classifyTrash(file: File): ClassifyResult {
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultiPart = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestImageFile
        )
        return apiClassifyService.classifyTrash(imageMultiPart)
    }

    suspend fun getAllCommunityPosts() = apiService.getAllCommunityPosts()

    suspend fun getCommunityLikes(id: Int) = apiService.getCommunityLikes(id)

    suspend fun getAComment(id: Int) = apiService.getAComment(id)
}
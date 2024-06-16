package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.data.remote.response.GetAllPostResponse
import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @Multipart
    @POST("community/")
    fun post(
        @Part("post") post: String,
        @Part("create_at") createdAt: String,
        @Part("post_img") postImg: MultipartBody.Part
    ): Call<CommunityPostResponse>

    @GET("/community/posts")
    suspend fun getAllCommunityPosts(
        @Header("Authorization") token: String
    ): GetAllPostResponse

}
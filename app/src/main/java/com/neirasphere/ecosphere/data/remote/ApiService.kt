package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.GetAllPostResponse
import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

//    @Multipart
//    @POST("community/")
//    suspend fun post(
//        @Part("post") post: String,
//        @Part("created_at") createdAt: String,
//        @Part("post_img") postImg: MultipartBody.Part
//    ): CommunityPostResponse

    @GET("/community/posts")
    suspend fun getAllCommunityPosts(
        @Header("Authorization") token: String
    ): GetAllPostResponse

}
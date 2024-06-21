package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.data.remote.response.GetACommentResponse
import com.neirasphere.ecosphere.data.remote.response.GetAllPostResponse
import com.neirasphere.ecosphere.data.remote.response.GetPostLikesResponse
import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @Multipart
    @POST("community/")
    suspend fun postWithImage(
        @Header("Authorization") token: String,
        @Part("post") post: String,
        @Part("post_img") postImg: MultipartBody.Part
    ): CommunityPostResponse

    @FormUrlEncoded
    @POST("community/")
    suspend fun post(
        @Header("Authorization") token: String,
        @Field("post") post: String
    ): CommunityPostResponse

    @GET("community/")
    suspend fun getAllCommunityPosts(): GetAllPostResponse

    @GET("community/likes/{id}")
    suspend fun getCommunityLikes(
        @Path("id") id: Int
    ): GetPostLikesResponse

    @GET("community/comments/{id}")
    suspend fun getAComment(
        @Path("id") id: Int
    ): GetACommentResponse

}
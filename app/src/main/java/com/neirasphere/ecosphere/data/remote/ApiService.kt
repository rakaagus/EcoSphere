package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.CommunityPostResponse
import com.neirasphere.ecosphere.data.remote.response.GetPostCommentsResponse
import com.neirasphere.ecosphere.data.remote.response.GetPostLikesResponse
import com.neirasphere.ecosphere.data.remote.response.GetPostsResponse
import com.neirasphere.ecosphere.data.remote.response.GetUserByIdResponse
import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import com.neirasphere.ecosphere.data.remote.response.PostResponse
import com.neirasphere.ecosphere.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.DELETE
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


    @GET("user/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): GetUserByIdResponse

    @Multipart
    @POST("community/")
    suspend fun postWithImage(
        @Header("Authorization") token: String,
        @Part("post") post: String,
        @Part post_img: MultipartBody.Part
    ): CommunityPostResponse

    @FormUrlEncoded
    @POST("community/")
    suspend fun post(
        @Header("Authorization") token: String,
        @Field("post") post: String
    ): CommunityPostResponse

    @GET("community/")
    suspend fun getAllCommunityPosts(): GetPostsResponse

    @GET("community/{id}")
    suspend fun getCommunityPost(
        @Path("id") id: Int
    ): GetPostsResponse

    @GET("community/likes/{id}")
    suspend fun getCommunityLikes(
        @Path("id") id: Int
    ): GetPostLikesResponse

    @GET("comments/{id}/comments")
    suspend fun getCommentsByPostId(
        @Path("id") id: Int
    ): GetPostCommentsResponse

    @FormUrlEncoded
    @POST("comments/{id}/comments")
    suspend fun postComment(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Field("comment") comment: String
    ): PostResponse

    @FormUrlEncoded
    @POST("{id}/like")
    suspend fun postLike(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): PostResponse

    @FormUrlEncoded
    @DELETE("{id}/unlike")
    suspend fun postUnlike(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): PostResponse

    @FormUrlEncoded
    @POST("user")
    suspend fun register(
        @Field("nama_depan") nama_depan: String,
        @Field("nama_belakang") nama_belakang: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("googleId") googleId: String? = null
    ) : RegisterResponse
}
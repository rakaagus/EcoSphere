package com.neirasphere.ecosphere.data.remote

import com.neirasphere.ecosphere.data.remote.response.LoginResponse
import com.neirasphere.ecosphere.data.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST
    suspend fun register(
        @Field("nama_depan") nama_depan: String,
        @Field("nama_belakang") nama_belakang: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : RegisterResponse
}
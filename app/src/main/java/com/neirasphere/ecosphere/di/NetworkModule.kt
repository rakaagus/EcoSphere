package com.neirasphere.ecosphere.di

import com.neirasphere.ecosphere.data.remote.ApiClassifyService
import com.neirasphere.ecosphere.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient) : ApiService {
        return Retrofit.Builder()
            .baseUrl("http://localhost:4000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClassifyService(client: OkHttpClient) : ApiClassifyService {
        return Retrofit.Builder()
            .baseUrl("https://neira-api.1hs5xuud6atv.us-south.codeengine.appdomain.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiClassifyService::class.java)
    }
}
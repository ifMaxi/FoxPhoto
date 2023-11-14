package com.maxidev.foximage.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxidev.foximage.data.repository.FoxRepository
import com.maxidev.foximage.data.repository.FoxRepositoryImpl
import com.maxidev.foximage.utils.Constants.BASE_URL
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val repository: FoxRepository
}

class DefAppContainer: AppContainer {
    private val contentType = "application/json".toMediaType()

    private val providesRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    private val providesApiService: ApiService by lazy {
        providesRetrofit.create(ApiService::class.java)
    }

    override val repository: FoxRepository by lazy {
        FoxRepositoryImpl(providesApiService)
    }
}
package com.maxidev.foximage.data.remote

import com.maxidev.foximage.data.model.FoxModel
import com.maxidev.foximage.utils.Constants.FLOOF
import retrofit2.http.GET

interface ApiService {
    @GET(FLOOF)
    suspend fun pointFloof(): FoxModel
}
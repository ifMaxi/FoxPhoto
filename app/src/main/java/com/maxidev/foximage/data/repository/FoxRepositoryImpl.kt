package com.maxidev.foximage.data.repository

import com.maxidev.foximage.data.model.FoxModel
import com.maxidev.foximage.data.remote.ApiService

class FoxRepositoryImpl(
    private val apiService: ApiService
): FoxRepository {
    override suspend fun getFox(): FoxModel = apiService.pointFloof()
}
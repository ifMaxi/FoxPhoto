package com.maxidev.foximage.data.repository

import com.maxidev.foximage.data.model.FoxModel

interface FoxRepository {
    suspend fun getFox(): FoxModel
}
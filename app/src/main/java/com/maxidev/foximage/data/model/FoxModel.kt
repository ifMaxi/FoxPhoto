package com.maxidev.foximage.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoxModel(
    @SerialName("image")
    val image: String,
    @SerialName("link")
    val link: String
)
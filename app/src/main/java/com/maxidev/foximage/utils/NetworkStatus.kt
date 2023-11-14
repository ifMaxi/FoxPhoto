package com.maxidev.foximage.utils

sealed interface NetworkStatus {
    data class Success(val onSuccess: Any): NetworkStatus
    data class Error(val onError: String): NetworkStatus
    data object Loading: NetworkStatus
}
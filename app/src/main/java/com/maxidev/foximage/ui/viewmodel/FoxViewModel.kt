package com.maxidev.foximage.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.maxidev.foximage.AppApplication
import com.maxidev.foximage.data.repository.FoxRepository
import com.maxidev.foximage.utils.NetworkStatus
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FoxViewModel(
    private val repository: FoxRepository
): ViewModel() {
    var netStatus: NetworkStatus by mutableStateOf(NetworkStatus.Loading)
        private set

    init {
        singleFox()
    }

    fun singleFox() {
        viewModelScope.launch {
            netStatus = NetworkStatus.Loading
            netStatus = try {
                NetworkStatus.Success(onSuccess = repository.getFox())
            } catch (io: IOException) {
                NetworkStatus.Error("Error")
            } catch (http: HttpException) {
                NetworkStatus.Error("Error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AppApplication)
                val repo = application.container.repository
                FoxViewModel(repo)
            }
        }
    }
}
package com.maxidev.foximage

import android.app.Application
import com.maxidev.foximage.data.remote.AppContainer
import com.maxidev.foximage.data.remote.DefAppContainer

class AppApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefAppContainer()
    }
}
package com.example.simplebooklibrary

import android.app.Application
import com.example.simplebooklibrary.di.KoinInitializer

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.initializeKoin(this@Application)
    }
}

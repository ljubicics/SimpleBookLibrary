package com.example.simplebooklibrary

import android.app.Application
import com.example.simplebooklibrary.di.KoinInitializer

class SimpleBookLibraryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.initializeKoin(this@SimpleBookLibraryApplication)
    }
}

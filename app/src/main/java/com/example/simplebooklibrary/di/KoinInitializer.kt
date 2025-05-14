package com.example.simplebooklibrary.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object KoinInitializer {

    fun initializeKoin(context: Context) = startKoin {
        androidLogger()

        androidContext(context)

        modules(
            AppModule.module,
            viewModelModule
        )
    }
}

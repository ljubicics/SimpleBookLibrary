package com.example.simplebooklibrary.di

import com.example.simplebooklibrary.viewer.util.IBitmapConverter
import com.example.simplebooklibrary.viewer.util.PdfBitmapConverter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {
    val module = module {
        single<IBitmapConverter> { PdfBitmapConverter(androidContext()) }
    }
}

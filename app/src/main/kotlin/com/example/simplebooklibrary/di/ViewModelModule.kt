package com.example.simplebooklibrary.di

import com.example.simplebooklibrary.viewer.ui.PdfViewerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PdfViewerViewModel(get()) }
}

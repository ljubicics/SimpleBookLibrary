package com.example.simplebooklibrary.viewer.ui

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebooklibrary.viewer.util.IBitmapConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PdfViewerViewModel(
    private val pdfBitmapConverter: IBitmapConverter
) : ViewModel() {

    private val _state = MutableStateFlow(PdfViewerContract.State())
    val state = _state.asStateFlow()

    private var pageIndex: Int = 0
    private var currentPage: Int = 0
    private val pagesList = mutableListOf<Bitmap>()

    fun setPdfUri(uri: Uri) {
        pagesList.clear()
        _state.update {
            it.copy(
                pdfUri = uri,

            )
        }
        renderPdf()
    }

    fun onPageSwipe(pageNumber: Int) {
        if (pageNumber >= currentPage) {
            currentPage++
            if (currentPage % 5 == 4) {
                updatePageIndex()
                renderPdf()
            }
        }
    }


    private fun updatePageIndex() {
        pageIndex++
    }

    private fun renderPdf() {
        viewModelScope.launch {
            pdfBitmapConverter.pdfToBitmaps(
                contentUri = state.value.pdfUri,
                page = pageIndex
            ).let { newPages ->
                pagesList.addAll(newPages)
                _state.update {
                    it.copy(
                        pages = pagesList.toList(),
                        totalPages = it.totalPages + newPages.size
                    )
                }
            }
        }
    }
}
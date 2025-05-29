package com.example.simplebooklibrary.viewer.ui

import android.graphics.Bitmap
import android.net.Uri

interface PdfViewerContract {
    data class State(
        val pdfUri: Uri = Uri.EMPTY,
        val totalPages: Int = 0,
        val pages: List<Bitmap> = emptyList(),
    )

    sealed class Action {
        data class SetPdfUri(val uri: Uri) : Action()
        data class OnPageSwipe(val pageNumber: Int) : Action()
    }
}

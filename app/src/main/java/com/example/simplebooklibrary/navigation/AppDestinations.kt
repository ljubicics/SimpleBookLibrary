package com.example.simplebooklibrary.navigation

import android.net.Uri
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

sealed class AppDestinations {
    data object Main : AppDestinations()
    data object BookDetails : AppDestinations()

    @Serializable
    data class PdfViewer( val uri: String) : AppDestinations()

    @Serializable
    data object Library : AppDestinations()

    @Serializable
    data object BookList : AppDestinations()
    data object BookSearch : AppDestinations()
    data object BookShelf : AppDestinations()
    data object BookShelfDetails : AppDestinations()
    data object BookShelfEdit : AppDestinations()
    data object BookShelfAdd : AppDestinations()
}

package com.example.simplebooklibrary.navigation

import kotlinx.serialization.Serializable

sealed class AppDestinations {
    data object Main : AppDestinations()
    data object BookDetails : AppDestinations()

    @Serializable
    data object PdfViewer : AppDestinations()

    @Serializable
    data object BookList : AppDestinations()
    data object BookSearch : AppDestinations()
    data object BookShelf : AppDestinations()
    data object BookShelfDetails : AppDestinations()
    data object BookShelfEdit : AppDestinations()
    data object BookShelfAdd : AppDestinations()
}
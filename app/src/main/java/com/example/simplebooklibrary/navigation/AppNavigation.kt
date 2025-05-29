package com.example.simplebooklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.simplebooklibrary.library.ui.LibraryScreen
import com.example.simplebooklibrary.viewer.ui.PdfViewerScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestinations.Library
    ) {
        composable<AppDestinations.Library> {
            LibraryScreen(
                onAddBook = { navController.navigate(AppDestinations.PdfViewer(it.toString())) },
                onOpenBook = { navController.navigate(AppDestinations.PdfViewer(it.toString())) })
        }
        composable<AppDestinations.PdfViewer> {
            val args = it.toRoute<AppDestinations.PdfViewer>()
            val uri = args.uri.toUri()

            PdfViewerScreen(
                uri = uri
            )
        }
    }
}

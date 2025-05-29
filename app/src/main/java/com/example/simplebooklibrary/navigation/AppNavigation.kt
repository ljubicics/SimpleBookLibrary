package com.example.simplebooklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplebooklibrary.viewer.ui.PdfViewerScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestinations.PdfViewer
    ) {
        composable<AppDestinations.PdfViewer> {
            PdfViewerScreen()
        }
    }
}

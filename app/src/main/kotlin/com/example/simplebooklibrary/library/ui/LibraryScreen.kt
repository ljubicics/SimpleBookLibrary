package com.example.simplebooklibrary.library.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Suppress("UnusedParameter")
@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    onAddBook: (Uri) -> Unit = {},
    onOpenBook: (Uri) -> Unit = {},
) {

    val choosePdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        onAddBook(uri ?: Uri.EMPTY)
    }

    LibraryScreenContent(
        modifier = modifier,
        onAddBookClick = {
            choosePdfLauncher.launch("application/pdf")
        }
    )
}

@Composable
fun LibraryScreenContent(
    modifier: Modifier = Modifier,
    onAddBookClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onAddBookClick
        ) {
            Text("Choose PDF")
        }
    }
}

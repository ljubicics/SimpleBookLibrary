package com.example.simplebooklibrary.viewer.ui

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun PdfViewerScreen(
    modifier: Modifier = Modifier,
    uri: Uri = Uri.EMPTY,
    viewModel: PdfViewerViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    val pagerState = rememberPagerState(
        pageCount = {
            state.totalPages
        }
    )

    LaunchedEffect(key1 = null) {
        viewModel.initialize(uri = uri)
    }

    LaunchedEffect(key1 = null) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.handleAction(PdfViewerContract.Action.OnPageSwipe(pageNumber = page))
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 0,
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                PdfPage(page = state.pages[page])
            }
        }
    }
}

@Composable
fun PdfPage(
    page: Bitmap,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = page,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(page.width.toFloat() / page.height.toFloat())
    )
}

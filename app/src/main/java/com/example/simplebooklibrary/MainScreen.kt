package com.example.simplebooklibrary

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.simplebooklibrary.navigation.AppNavigation

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        AppNavigation(
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }
}
package com.example.fetchapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fetchapplication.presentation.composables.ItemListScreen
import com.example.fetchapplication.presentation.ItemListViewModel
import com.example.fetchapplication.presentation.UIState
import com.example.fetchapplication.ui.theme.FetchApplicationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val itemListViewModel = koinViewModel<ItemListViewModel>()
            val uiState by itemListViewModel.uiState.collectAsState(UIState())

            FetchApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemListScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = uiState
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemListScreenPreview() {
    FetchApplicationTheme {
        ItemListScreen(uiState = UIState())
    }
}
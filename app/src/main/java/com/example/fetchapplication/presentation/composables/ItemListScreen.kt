package com.example.fetchapplication.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetchapplication.R
import com.example.fetchapplication.presentation.UIState

@Composable
fun ItemListScreen(
    modifier: Modifier = Modifier,
    uiState: UIState
) {
    if (uiState.isLoading) LoadingAnimation()
    if (uiState.isError?.isNotEmpty() == true) ErrorScreen()
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(
                stringResource(R.string.list_id),
                stringResource(R.string.name),
                stringResource(R.string.id)
            ).forEach { header ->
                HeaderText(header)
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            uiState.itemList?.forEach { item ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    listOf(item.listId.toString(), item.name, item.id.toString()).forEach {
                        it?.let { ItemText(it) }
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun ItemText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        textAlign = TextAlign.End
    )
}
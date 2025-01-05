package com.example.fetchapplication.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {

            Text(
                stringResource(R.string.view_instruction),
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )

            uiState.mapItemList?.map {
                val displayItems = remember { mutableStateOf(false) }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .clickable { displayItems.value = !displayItems.value },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeaderText(stringResource(R.string.list_id, it.key.toString()))
                    Icon(
                        imageVector = if (displayItems.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = ""
                    )
                }
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    if (displayItems.value)
                        it.value.map { item ->
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                ItemText(item.name.toString())
                                ItemText(item.id.toString())
                            }
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
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun ItemText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp
    )
}
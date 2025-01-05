package com.example.fetchapplication.presentation

import com.example.fetchapplication.data.Item

data class UIState(
    val isLoading: Boolean = false,
    val isError: String? = "",
    val itemList : List<Item>? = listOf()
)

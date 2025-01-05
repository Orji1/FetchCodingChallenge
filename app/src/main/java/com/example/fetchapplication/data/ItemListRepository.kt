package com.example.fetchapplication.data

import com.example.fetchapplication.utility.Resource
import kotlinx.coroutines.flow.Flow

interface ItemListRepository {
    suspend fun getItemList() : Flow<Resource<List<Item>>>
}
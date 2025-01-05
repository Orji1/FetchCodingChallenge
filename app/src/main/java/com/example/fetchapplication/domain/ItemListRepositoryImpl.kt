package com.example.fetchapplication.domain

import com.example.fetchapplication.data.ItemListApi
import com.example.fetchapplication.data.ItemListRepository
import com.example.fetchapplication.utility.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ItemListRepositoryImpl(private val itemListApi: ItemListApi) :
    ItemListRepository {
    override suspend fun getItemList() = flow {
        emit(Resource.Loading())
        val result = itemListApi.getItemList()
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }
}
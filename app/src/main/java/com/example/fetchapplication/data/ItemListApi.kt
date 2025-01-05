package com.example.fetchapplication.data

import com.example.fetchapplication.utility.END_POINT
import retrofit2.http.GET

interface ItemListApi {
    @GET(END_POINT)
    suspend fun getItemList(): List<Item>
}
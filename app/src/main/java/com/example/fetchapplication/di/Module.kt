package com.example.fetchapplication.di

import com.example.fetchapplication.data.ItemListApi
import com.example.fetchapplication.data.ItemListRepository
import com.example.fetchapplication.domain.ItemListRepositoryImpl
import com.example.fetchapplication.presentation.ItemListViewModel
import com.example.fetchapplication.utility.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    fun provideItemListApi() : ItemListApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ItemListApi::class.java)

    fun provideItemListRepository(itemListApi: ItemListApi) : ItemListRepository = ItemListRepositoryImpl(itemListApi)

    single { provideItemListApi() }
    singleOf(::ItemListRepositoryImpl).bind<ItemListRepository>()
    viewModelOf(::ItemListViewModel)
}
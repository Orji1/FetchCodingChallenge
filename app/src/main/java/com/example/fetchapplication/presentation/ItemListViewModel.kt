package com.example.fetchapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapplication.data.Item
import com.example.fetchapplication.data.ItemListRepository
import com.example.fetchapplication.utility.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemListViewModel(private val itemListRepository: ItemListRepository) : ViewModel() {

    companion object {
        const val TAG = "ItemListViewModel"
    }

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        getItemList()
    }

    /**
     * Extension class to sort the list by its listId and then by its name
     * while filtering out all null and blank names
     */
    private fun List<Item>.sortByListId() = filterNot {
        it.name.isNullOrBlank()
    }.sortedWith(compareBy<Item> { it.listId }.thenBy { it.name?.split(" ")?.get(1)?.toInt() })

    private fun getItemList() {
        viewModelScope.launch {
            itemListRepository.getItemList().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                        Log.d(TAG, "Loading ${_uiState.value.isLoading}")
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                itemList = result.data?.sortByListId()
                            )
                        }
                        Log.d(TAG, "Success ${_uiState.value.itemList}")
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false, isError = result.message.toString())
                        }
                        Log.d(TAG, "Error ${_uiState.value.isError}")
                    }
                }

            }

        }
    }
}
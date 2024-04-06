package com.target.bhavesh.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.bhavesh.ui.screens.home.usecase.FetchProductsUseCase
import com.target.bhavesh.util.Resource.Error
import com.target.bhavesh.util.Resource.Loading
import com.target.bhavesh.util.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchProductUseCase: FetchProductsUseCase) : ViewModel() {

    private val _state = mutableStateOf(HomeUIState())
    val state: State<HomeUIState> = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            fetchProductUseCase.execute().collect {
                when (it) {
                    is Success -> {
                        _state.value = it.data?.let { data ->
                            HomeUIState(products = data)
                        } ?: HomeUIState(error = "Data not available")
                    }

                    is Error -> _state.value = HomeUIState(error = it.message ?: "Error")
                    is Loading -> _state.value = HomeUIState(isLoading = true)
                }
            }
        }
    }

    fun retry() {
        getProducts()
    }
}
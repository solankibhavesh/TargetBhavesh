package com.target.bhavesh.ui.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.bhavesh.ui.screens.details.usecase.FetchProductDetailsUseCase
import com.target.bhavesh.util.Constants.DEALS_ID
import com.target.bhavesh.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fetchProductDetails: FetchProductDetailsUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DetailsUIState())
    val state: State<DetailsUIState> = _state

    private val productId by lazy { stateHandle.get<String>(DEALS_ID) }

    init {
        productId?.let { getProductDetails(it) }
    }

    private fun getProductDetails(productId: String) {
        viewModelScope.launch {
            fetchProductDetails.execute(productId).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = it.data?.let { data ->
                            DetailsUIState(productDetails = data)
                        } ?: DetailsUIState(error = "Data not available")
                    }

                    is Resource.Error -> _state.value = DetailsUIState(error = it.message ?: "Error")
                    is Resource.Loading -> _state.value = DetailsUIState(isLoading = true)
                }
            }
        }
    }

    fun retry() {
        productId?.let { getProductDetails(it) }
    }
}
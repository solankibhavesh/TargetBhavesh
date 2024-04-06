package com.target.bhavesh.ui.screens.details

import com.target.bhavesh.domain.models.ProductDetails

data class DetailsUIState(
    val isLoading: Boolean = false,
    val error: String = "",
    val productDetails: ProductDetails? = null
)
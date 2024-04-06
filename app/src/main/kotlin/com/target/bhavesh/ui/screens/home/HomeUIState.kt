package com.target.bhavesh.ui.screens.home

import com.target.bhavesh.domain.models.Product

data class HomeUIState(
    val isLoading: Boolean = false,
    val error: String = "",
    val products: List<Product> = emptyList(),
)
package com.target.bhavesh.domain.repository

import com.target.bhavesh.domain.models.ProductDetails
import com.target.bhavesh.domain.models.Products
import retrofit2.Response

interface NetworkRepository {
    suspend fun getProducts(): Response<Products>
    suspend fun getProductDetails(productId: String): Response<ProductDetails>
}



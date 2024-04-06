package com.target.bhavesh.domain.repository

import com.target.bhavesh.domain.ProductApi
import com.target.bhavesh.domain.models.ProductDetails
import com.target.bhavesh.domain.models.Products
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val api: ProductApi) : NetworkRepository {
    override suspend fun getProducts(): Response<Products> {
        return api.getProducts()
    }

    override suspend fun getProductDetails(productId: String): Response<ProductDetails> {
        return api.getProductDetails(productId)
    }
}
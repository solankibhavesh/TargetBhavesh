package com.target.bhavesh.domain

import com.target.bhavesh.domain.models.ProductDetails
import com.target.bhavesh.domain.models.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("deals")
    suspend fun getProducts(): Response<Products>

    @GET("deals/{productId}")
    suspend fun getProductDetails(@Path("productId") productId: String): Response<ProductDetails>
}
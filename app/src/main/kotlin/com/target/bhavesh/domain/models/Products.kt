package com.target.bhavesh.domain.models

import com.google.gson.annotations.SerializedName

data class Products(
    val products: List<Product>?
)

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val aisle: String,
    @SerializedName("image_url") val imageUrl: String?,
    val fulfillment: String,
    val availability: String,
    @SerializedName("regular_price") val regularPrice: ProductPrice,
    @SerializedName("sale_price") val salePrice: ProductPrice?,
)

data class ProductPrice(
    @SerializedName("display_string") val displayPrice: String
)
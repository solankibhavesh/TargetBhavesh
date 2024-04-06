package com.target.bhavesh.domain.models

import com.google.gson.annotations.SerializedName

data class ProductDetails(
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
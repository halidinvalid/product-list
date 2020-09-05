package com.tech.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("product_id") var productId: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("price") var price: Int?,
    @SerializedName("image") var image: String?,
    @SerializedName("description") var description: String?
)
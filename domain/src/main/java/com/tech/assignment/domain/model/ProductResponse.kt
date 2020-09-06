package com.tech.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products") var products: List<ProductItem?>
)
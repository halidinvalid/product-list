package com.tech.assignment.domain.repositories

import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import retrofit2.Response

interface ProductRepository {
    suspend fun getProducts(): Response<ProductResponse?>

    suspend fun getProductDetails(productId: String?): Response<ProductItem?>

}
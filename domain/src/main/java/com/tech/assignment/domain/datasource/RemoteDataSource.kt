package com.tech.assignment.domain.datasource

import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getProducts(): Response<ProductResponse?>

    suspend fun getProductDetails(productId: String?): Response<ProductItem?>

}
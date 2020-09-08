package com.tech.assignment.domain.datasource

import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse

interface LocalDataSource {

    suspend fun getProductsCache(): ProductResponse?

    suspend fun getProductDetailsCache(productId: String?): ProductItem?

    suspend fun updateData(products: List<ProductItem?>)

}
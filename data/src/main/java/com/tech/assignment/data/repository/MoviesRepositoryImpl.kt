package com.tech.assignment.data.repository

import com.tech.assignment.data.services.ProductServices
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import com.tech.assignment.domain.repositories.ProductRepository
import retrofit2.Response

class ProductRepositoryImpl constructor(private val productServices: ProductServices) :
    ProductRepository {

    override suspend fun getProducts(
    ): Response<ProductResponse?> {
        return productServices.products()
    }

    override suspend fun getProductDetails(productId: String?): Response<ProductItem?> {
        return productServices.productDetails(productId)
    }
}

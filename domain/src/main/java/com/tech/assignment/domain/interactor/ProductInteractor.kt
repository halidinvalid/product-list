package com.tech.assignment.domain.interactor

import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import com.tech.assignment.domain.repositories.ProductRepository
import retrofit2.Response

class ProductInteractor(private var repositories: ProductRepository) {

    suspend fun getProducts(): Response<ProductResponse?> {
        return repositories.getProducts()
    }

    suspend fun getProductDetails(productId: String?): Response<ProductItem?> {
        return repositories.getProductDetails(productId)
    }


}
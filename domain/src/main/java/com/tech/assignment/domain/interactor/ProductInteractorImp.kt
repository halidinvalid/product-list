package com.tech.assignment.domain.interactor

import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import com.tech.assignment.domain.repositories.ProductRepository
import retrofit2.Response

class ProductInteractorImp(private var repositories: ProductRepository) : ProductInteractor {

    override suspend fun getProducts(): Response<ProductResponse?>? {
        return repositories.getProducts()
    }

    override suspend fun getProductDetails(productId: String?): Response<ProductItem?> {
        return repositories.getProductDetails(productId)
    }


}
package com.tech.assignment.data.datasource

import com.tech.assignment.data.services.ProductServices
import com.tech.assignment.domain.datasource.RemoteDataSource

class RemoteDataSourceImp(private val productServices: ProductServices) : RemoteDataSource {

    override suspend fun getProducts() = productServices.products()

    override suspend fun getProductDetails(productId: String?) =
        productServices.productDetails(productId)

}
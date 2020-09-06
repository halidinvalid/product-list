package com.tech.assignment.data.datasource

import com.tech.assignment.data.services.ProductServices

class RemoteDataSourceImp(private val productServices: ProductServices) : RemoteDataSource {

    override suspend fun getProducts() = productServices.products()

    override suspend fun getProductDetails(productId: String?) =
        productServices.productDetails(productId)

}
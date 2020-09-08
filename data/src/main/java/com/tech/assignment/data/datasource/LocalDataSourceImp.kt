package com.tech.assignment.data.datasource

import com.tech.assignment.data.dao.ProductsDao
import com.tech.assignment.domain.datasource.LocalDataSource
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse

class LocalDataSourceImp(private val dao: ProductsDao) : LocalDataSource {

    override suspend fun getProductsCache() = ProductResponse(dao.getAllProductsFromCache())

    override suspend fun getProductDetailsCache(productId: String?) =
        dao.getProductDetailsFromCache(productId)

    override suspend fun updateData(products: List<ProductItem?>) = dao.updateData(products)
}
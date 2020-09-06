package com.tech.assignment.data.repository

import android.content.Context
import com.tech.assignment.data.datasource.LocalDataSource
import com.tech.assignment.data.datasource.RemoteDataSource
import com.tech.assignment.data.extensions.isOnline
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import com.tech.assignment.domain.repositories.ProductRepository
import okhttp3.ResponseBody
import retrofit2.Response

class ProductRepositoryImpl constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) : ProductRepository {

    override suspend fun getProducts(
    ): Response<ProductResponse?> {
        return if (isOnline(context)) {
            val productsResponse = remoteDataSource.getProducts()
            localDataSource.updateData(productsResponse.body()?.products!!)
            productsResponse
        } else {
            val productListCache = localDataSource.getProductsCache()
            if (productListCache?.products.isNullOrEmpty()) { // if db null & network connection error..
                Response.error(3002, ResponseBody.create(null, ""))
            } else {
                Response.success(productListCache)
            }
        }
    }

    override suspend fun getProductDetails(productId: String?): Response<ProductItem?> {
        return if (isOnline(context)) {
            remoteDataSource.getProductDetails(productId)
        } else {
            val productDetailsCache = localDataSource.getProductDetailsCache(productId)
            if (productDetailsCache == null) {
                Response.error(3002, ResponseBody.create(null, ""))
            } else {
                Response.success(productDetailsCache)
            }
        }
    }
}

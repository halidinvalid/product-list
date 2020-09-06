package com.tech.assignment.data.repository

import android.content.Context
import com.tech.assignment.data.dao.ProductsDao
import com.tech.assignment.data.extensions.isOnline
import com.tech.assignment.data.services.ProductServices
import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import com.tech.assignment.domain.repositories.ProductRepository
import okhttp3.ResponseBody
import retrofit2.Response

class ProductRepositoryImpl constructor(
    private val productServices: ProductServices,
    private val context: Context,
    private val dao: ProductsDao
) : ProductRepository {

    override suspend fun getProducts(
    ): Response<ProductResponse?> {
        return if (isOnline(context)) {
            val productsResponse = productServices.products()
            dao.updateData(productsResponse.body()?.products!!)
            productsResponse
        } else {
            val productListCache = getProductsCache()
            if (productListCache?.products.isNullOrEmpty()) { // if db null & network connection error..
                Response.error(3002, ResponseBody.create(null, ""))
            } else {
                Response.success(productListCache)
            }
        }
    }

    override suspend fun getProductDetails(productId: String?): Response<ProductItem?> {
        return if (isOnline(context)) {
            productServices.productDetails(productId)
        } else {
            val productDetailsCache = getProductDetailsCache(productId)
            if (productDetailsCache == null) {
                Response.error(3002, ResponseBody.create(null, ""))
            } else {
                Response.success(productDetailsCache)
            }
        }
    }

    override suspend fun getProductsCache(): ProductResponse? {
        return ProductResponse(dao.getAllProductsFromCache())
    }

    override suspend fun getProductDetailsCache(productId: String?): ProductItem? {
        return dao.getProductDetailsFromCache(productId)
    }
}

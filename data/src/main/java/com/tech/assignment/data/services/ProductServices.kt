package com.tech.assignment.data.services

import com.tech.assignment.domain.model.ProductItem
import com.tech.assignment.domain.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductServices {

    @GET("/developer-application-test/cart/list")
    suspend fun products(): Response<ProductResponse?>

    @GET("/developer-application-test/cart/{product_id}/detail")
    suspend fun productDetails(@Path("product_id") productId: String?): Response<ProductItem?>

}

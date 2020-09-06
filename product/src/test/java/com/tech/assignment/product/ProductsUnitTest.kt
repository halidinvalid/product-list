package com.tech.assignment.product

import com.tech.assignment.data.services.ProductServices
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProductsUnitTest {

    private lateinit var productService: ProductServices

    @Test
    fun testGetProductsSuccess() = runBlocking {
        productService = RetrofitHelper.getRetrofit(BASE_URL)
        assert(productService.products().isSuccessful)
    }

    @Test
    fun testGetProductsFailed() = runBlocking {
        productService = RetrofitHelper.getRetrofit(FAKE_URL)
        assert(productService.products().isSuccessful)
    }

    @Test
    fun testGetProductDetailsSuccess() = runBlocking {
        productService = RetrofitHelper.getRetrofit(BASE_URL)
        assert(productService.productDetails(VALID_PRODUCT_ID).isSuccessful)
    }

    @Test
    fun testGetProductDetailsFailed() = runBlocking {
        productService = RetrofitHelper.getRetrofit(BASE_URL)
        assert(productService.productDetails(INVALID_PRODUCT_ID).isSuccessful)
    }

    companion object {
        private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"
        private const val FAKE_URL = "fake-url"
        private const val VALID_PRODUCT_ID = "1"
        private const val INVALID_PRODUCT_ID = "-1"
    }
}

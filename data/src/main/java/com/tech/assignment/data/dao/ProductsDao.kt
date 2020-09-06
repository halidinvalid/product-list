package com.tech.assignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.tech.assignment.domain.model.ProductItem

@Dao
interface ProductsDao {

    @Transaction
    fun updateData(products: List<ProductItem?>) {
        deleteAll()
        insertAll(products)
    }

    @Insert
    fun insertAll(products: List<ProductItem?>)

    @Query("DELETE FROM ProductsTable")
    fun deleteAll()

    @Query("SELECT * FROM ProductsTable")
    fun getAllProductsFromCache(): List<ProductItem?>

    @Query("SELECT * FROM ProductsTable WHERE productId = :productId")
    fun getProductDetailsFromCache(productId: String?): ProductItem?
}

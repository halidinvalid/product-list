package com.tech.assignment.data.dao


import androidx.room.Database
import androidx.room.RoomDatabase
import com.tech.assignment.domain.model.ProductItem

@Database(
    entities = [ProductItem::class],
    version = 1
)

abstract class ProductsDataBase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}
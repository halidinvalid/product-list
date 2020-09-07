package com.tech.assignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "ProductsTable")
data class ProductItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("product_id") var productId: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("price") var price: Int?,
    @SerializedName("image") var image: String?,
    @SerializedName("description") var description: String?
)



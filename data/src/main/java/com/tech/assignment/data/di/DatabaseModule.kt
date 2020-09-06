package com.tech.assignment.data.di


import androidx.room.Room
import com.tech.assignment.data.dao.ProductsDataBase
import org.koin.dsl.module.module

val databaseModule = module {


    single {
        Room.databaseBuilder(get(), ProductsDataBase::class.java, "productdb").build()
    }

    single { get<ProductsDataBase>().productsDao() }


}

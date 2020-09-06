package com.tech.assignment.data.di

import android.content.Context
import com.tech.assignment.data.dao.ProductsDao
import com.tech.assignment.data.repository.ProductRepositoryImpl
import com.tech.assignment.data.services.ProductServices
import com.tech.assignment.domain.repositories.ProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val productDataModules = module {

    fun provideProductsRepository(
        productServices: ProductServices,
        context: Context,
        dao: ProductsDao
    ): ProductRepository =
        ProductRepositoryImpl(productServices = productServices, context = context, dao = dao)
    single {
        provideProductsRepository(productServices = get(), context = androidContext(), dao = get())
    }

}
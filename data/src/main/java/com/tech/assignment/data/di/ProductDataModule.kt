package com.tech.assignment.data.di

import com.tech.assignment.data.repository.ProductRepositoryImpl
import com.tech.assignment.data.services.ProductServices
import com.tech.assignment.domain.repositories.ProductRepository
import org.koin.dsl.module.module


val productDataModules = module {
    single {
        provideProductsRepository(productServices = get())
    }
}


private fun provideProductsRepository(productServices: ProductServices): ProductRepository =
    ProductRepositoryImpl(productServices = productServices)

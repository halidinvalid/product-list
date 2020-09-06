package com.tech.assignment.domain.di

import com.tech.assignment.domain.interactor.ProductInteractor
import com.tech.assignment.domain.interactor.ProductInteractorImp
import org.koin.dsl.module.module

val productDomainModules = module {
    single<ProductInteractor> { ProductInteractorImp(get()) }

}

const val PRODUCT_INTERACTOR = "productInteractor"

package com.tech.assignment.domain.di

import com.tech.assignment.domain.interactor.ProductInteractor
import org.koin.dsl.module.module

val productDomainModules = module {
    factory(name = PRODUCT_INTERACTOR) {
        ProductInteractor(repositories = get())
    }

}

const val PRODUCT_INTERACTOR = "productInteractor"

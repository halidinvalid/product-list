package com.tech.assignment.product.di

import com.tech.assignment.product.ui.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val productViewModelModules = module {
    viewModel {
        ProductViewModel(
            get()
        )
    }
}

private const val PRODUCT_INTERACTOR = "productInteractor"

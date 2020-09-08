package com.tech.assignment.data.di

import android.content.Context
import com.tech.assignment.data.repository.ProductRepositoryImpl
import com.tech.assignment.domain.datasource.LocalDataSource
import com.tech.assignment.domain.datasource.RemoteDataSource
import com.tech.assignment.domain.repositories.ProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val productDataModules = module {

    fun provideProductsRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        context: Context
    ): ProductRepository =
        ProductRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            context = context
        )
    single {
        provideProductsRepository(
            localDataSource = get(),
            remoteDataSource = get(),
            context = androidContext()
        )
    }

}
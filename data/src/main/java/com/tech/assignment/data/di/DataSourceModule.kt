package com.tech.assignment.data.di

import com.tech.assignment.data.datasource.LocalDataSource
import com.tech.assignment.data.datasource.LocalDataSourceImp
import com.tech.assignment.data.datasource.RemoteDataSource
import com.tech.assignment.data.datasource.RemoteDataSourceImp
import org.koin.dsl.module.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImp(get()) }
    single<LocalDataSource> { LocalDataSourceImp(get()) }
}
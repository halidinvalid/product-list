package com.tech.assignment.di

import com.tech.assignment.data.di.apiModule
import com.tech.assignment.data.di.dataSourceModule
import com.tech.assignment.data.di.databaseModule
import com.tech.assignment.data.di.productDataModules


val appDataModule = listOf(
    databaseModule,
    apiModule,
    productDataModules,
    dataSourceModule
)
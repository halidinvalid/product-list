package com.tech.assignment.di

import com.tech.assignment.data.di.apiModule
import com.tech.assignment.data.di.productDataModules


val appDataModule = listOf(
    apiModule,
    productDataModules
)
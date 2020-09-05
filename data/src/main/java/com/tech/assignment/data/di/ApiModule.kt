package com.tech.assignment.data.di

import com.tech.assignment.data.api.createNetworkClient
import com.tech.assignment.data.services.ProductServices
import org.koin.dsl.module.module
import retrofit2.Retrofit

val apiModule = module {
    single(name = RETROFIT_INSTANCE) {
        createNetworkClient(BASE_URL)
    }
    single(name = API) {
        (get(RETROFIT_INSTANCE) as Retrofit).create(ProductServices::class.java)
    }
}

private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
package com.tech.assignment.data.api

import com.tech.assignment.data.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


fun createNetworkClient(baseUrl: String) =
    retrofitClient(
        baseUrl,
        httpClient()
    )

class BasicAuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl =
            request.url().newBuilder().build()
        val newRequest = request.newBuilder().url(newUrl).build()

        return try {
            chain.proceed(newRequest)
        } catch (e: IOException) { // custom exception error
            Response.Builder()
                .code(1007)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(ResponseBody.create(MediaType.parse("{}"), "{}"))
                .message(e.localizedMessage)
                .build()
        }
    }
}

private fun httpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.addInterceptor(BasicAuthInterceptor())
    clientBuilder.readTimeout(120, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
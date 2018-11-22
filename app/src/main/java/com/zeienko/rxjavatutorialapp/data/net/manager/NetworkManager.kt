package com.zeienko.rxjavatutorialapp.data.net.manager

import com.google.gson.GsonBuilder
import com.zeienko.rxjavatutorialapp.BuildConfig
import com.zeienko.rxjavatutorialapp.data.net.api.AlgoliaApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private var algoliaApi: AlgoliaApi

    init {
        val okHttpClient = initClient()
        algoliaApi = getApi(okHttpClient, AlgoliaApi::class.java)
    }

    private fun initClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor { chain ->
                val request: Request
                val original = chain.request()
                val requestBuilder = original
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")

                request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    private fun <T> getApi(okHttpClient: OkHttpClient, tClass: Class<T>): T {
        val gson = GsonBuilder()
            .create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(tClass)
    }

    fun getAlgoliaApi(): AlgoliaApi {
        return algoliaApi
    }
}
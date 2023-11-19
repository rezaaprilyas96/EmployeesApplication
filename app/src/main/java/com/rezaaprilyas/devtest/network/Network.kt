package com.rezaaprilyas.devtest.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.rezaaprilyas.devtest.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    fun retrofit(baseUrl: String = BuildConfig.API_BASE_URL, chuckerInterceptor: ChuckerInterceptor): Retrofit {
        val gsonBuilder = GsonBuilder()
        val converterFactory = GsonConverterFactory.create(gsonBuilder.create())
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient(chuckerInterceptor))
            .build()
    }

    private fun createMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    private fun okHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(NetworkInterceptor())
            .addInterceptor(chuckerInterceptor)
            .pingInterval(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(createLoggingInterceptor())
        }
        return okHttpBuilder.build()
    }


    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
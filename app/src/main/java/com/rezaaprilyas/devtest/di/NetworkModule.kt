package com.rezaaprilyas.devtest.di

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.rezaaprilyas.devtest.data.routes.ApiServices
import com.rezaaprilyas.devtest.network.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesCoreContext(application: Application) : Context = application

    @Provides
    @Singleton
    fun provideCoreChuckerInterceptor(
        context: Context,
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    }

    @Provides
    fun provideApiServices(chuckerInterceptor: ChuckerInterceptor): ApiServices = Network.retrofit(chuckerInterceptor = chuckerInterceptor).create(ApiServices::class.java)
}
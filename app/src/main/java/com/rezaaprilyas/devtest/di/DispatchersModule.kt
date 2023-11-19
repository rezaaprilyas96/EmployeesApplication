package com.rezaaprilyas.devtest.di

import com.rezaaprilyas.devtest.utils.dispatcher.AppDispatchers
import com.rezaaprilyas.devtest.utils.dispatcher.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    fun provideAppDispatchers(): DispatchersProvider = AppDispatchers()
}
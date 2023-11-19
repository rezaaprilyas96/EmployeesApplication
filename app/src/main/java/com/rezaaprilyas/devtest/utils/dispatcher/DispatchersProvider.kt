package com.rezaaprilyas.devtest.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val ui: CoroutineDispatcher
    val io: CoroutineDispatcher
}
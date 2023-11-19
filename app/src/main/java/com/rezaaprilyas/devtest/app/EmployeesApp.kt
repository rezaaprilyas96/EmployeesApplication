package com.rezaaprilyas.devtest.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmployeesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Companion.applicationContext = applicationContext
    }

    companion object {
        lateinit var applicationContext: Context
    }
}
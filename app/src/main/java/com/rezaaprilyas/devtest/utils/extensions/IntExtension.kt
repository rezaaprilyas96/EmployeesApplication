package com.rezaaprilyas.devtest.utils.extensions

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.rezaaprilyas.devtest.app.EmployeesApp

@ColorInt
fun Int.asColor(): Int {
    return try {
        ContextCompat.getColor(EmployeesApp.applicationContext, this)
    } catch (exception: Resources.NotFoundException) {
        exception.printStackTrace()
        this
    }
}
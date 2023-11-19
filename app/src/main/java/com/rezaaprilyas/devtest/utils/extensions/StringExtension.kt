package com.rezaaprilyas.devtest.utils.extensions

import java.text.NumberFormat
import java.util.Locale

fun String.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    formatRupiah.maximumFractionDigits = 0
    return try {
        formatRupiah.format(this.toDouble())
    } catch (e: Exception) {
        "-"
    }
}
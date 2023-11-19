package com.rezaaprilyas.devtest.data.employees.dataseource.response

import com.google.gson.annotations.SerializedName


data class EmployeesUpdateResponse(
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("data")
    val data : EmployeesUpdateDataResponse? = null,
    @field:SerializedName("message")
    val message : String? = null
)
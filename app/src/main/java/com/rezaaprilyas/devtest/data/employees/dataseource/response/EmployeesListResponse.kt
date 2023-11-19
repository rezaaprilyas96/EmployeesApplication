package com.rezaaprilyas.devtest.data.employees.dataseource.response

import com.google.gson.annotations.SerializedName


data class EmployeesListResponse(
    @field:SerializedName("data")
    val data : List<EmployeesLitstDataResponse>? = null,
    @field:SerializedName("status")
    val status: String? = null
)
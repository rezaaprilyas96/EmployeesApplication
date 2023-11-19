package com.rezaaprilyas.devtest.data.employees.dataseource.response

import com.google.gson.annotations.SerializedName

data class EmployeesUpdateDataResponse(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("salary")
    val salary: String? = null,
    @field:SerializedName("age")
    val age: String? = null
)

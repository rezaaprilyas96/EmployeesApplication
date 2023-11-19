package com.rezaaprilyas.devtest.data.employees.dataseource.request

import com.google.gson.annotations.SerializedName

data class EmployeesUpdateRequest(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("salary")
    val salary: String? = null,
    @field:SerializedName("age")
    val age: String? = null
)

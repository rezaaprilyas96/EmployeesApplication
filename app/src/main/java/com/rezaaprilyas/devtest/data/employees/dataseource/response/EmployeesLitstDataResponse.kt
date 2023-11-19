package com.rezaaprilyas.devtest.data.employees.dataseource.response

import com.google.gson.annotations.SerializedName

data class EmployeesLitstDataResponse(
    @field:SerializedName("employee_age")
    val employeeAge: String? = null,
    @field:SerializedName("employee_name")
    val employeeName: String? = null,
    @field:SerializedName("employee_salary")
    val employeeSalary: String? = null,
    @field:SerializedName("id")
    val id: String? = null
)

package com.rezaaprilyas.devtest.data.employees.dataseource

import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesInsertResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesDeleteResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesListResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesUpdateResponse
import retrofit2.Response

interface EmployeesRemoteDataSource {
    suspend fun getListEmployees(): Response<EmployeesListResponse>
    suspend fun deleteEmployee(employeeId: Int): Response<EmployeesDeleteResponse>
    suspend fun insertEmployee(request: EmployeesInsertRequest): Response<EmployeesInsertResponse>
    suspend fun updateEmployee(employeeId: Int, request: EmployeesUpdateRequest): Response<EmployeesUpdateResponse>
}
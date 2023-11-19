package com.rezaaprilyas.devtest.data.employees.dataseource

import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesInsertResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesDeleteResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesListResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesUpdateResponse
import com.rezaaprilyas.devtest.data.routes.ApiServices
import retrofit2.Response
import javax.inject.Inject

class EmployeesRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
) : EmployeesRemoteDataSource {

    override suspend fun getListEmployees(): Response<EmployeesListResponse> {
        return apiServices.getListEmployees()
    }

    override suspend fun deleteEmployee(employeeId: Int): Response<EmployeesDeleteResponse> {
        return apiServices.deleteEmployee(employeeId)
    }

    override suspend fun insertEmployee(request: EmployeesInsertRequest): Response<EmployeesInsertResponse> {
        return apiServices.insertEmployee(request)
    }

    override suspend fun updateEmployee(employeeId: Int, request: EmployeesUpdateRequest): Response<EmployeesUpdateResponse> {
        return apiServices.updateEmployee(employeeId, request)
    }
}
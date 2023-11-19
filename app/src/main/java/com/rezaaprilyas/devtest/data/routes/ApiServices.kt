package com.rezaaprilyas.devtest.data.routes

import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesDeleteResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesInsertResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesListResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesUpdateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServices {
    @GET("employees")
    suspend fun getListEmployees(): Response<EmployeesListResponse>

    @POST("create")
    suspend fun insertEmployee(@Body request: EmployeesInsertRequest): Response<EmployeesInsertResponse>

    @DELETE("delete/{id}")
    suspend fun deleteEmployee(@Path("id") employeeId: Int): Response<EmployeesDeleteResponse>

    @PUT("update/{id}")
    suspend fun updateEmployee(@Path("id") employeeId: Int, @Body request: EmployeesUpdateRequest): Response<EmployeesUpdateResponse>
}
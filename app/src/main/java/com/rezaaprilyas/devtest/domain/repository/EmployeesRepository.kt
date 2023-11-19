package com.rezaaprilyas.devtest.domain.repository

import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesDeleteResponse
import com.rezaaprilyas.devtest.domain.model.EmployeesDeleteModel
import com.rezaaprilyas.devtest.domain.model.EmployeesInsertModel
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.domain.model.EmployeesUpdateModel
import com.rezaaprilyas.devtest.utils.state.ResultState

interface EmployeesRepository {
    suspend fun getEmployeeList(): ResultState<List<EmployeesModel>>
    suspend fun deleteEmployee(employeeId: Int): ResultState<EmployeesDeleteModel>
    suspend fun insertEmployee(request: EmployeesInsertRequest): ResultState<EmployeesInsertModel>
    suspend fun updateEmployee(employeeId: Int, request: EmployeesUpdateRequest): ResultState<EmployeesUpdateModel>
}
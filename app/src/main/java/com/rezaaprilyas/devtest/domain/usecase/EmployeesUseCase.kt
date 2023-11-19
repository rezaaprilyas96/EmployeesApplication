package com.rezaaprilyas.devtest.domain.usecase

import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.domain.model.EmployeesDeleteModel
import com.rezaaprilyas.devtest.domain.model.EmployeesInsertModel
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.domain.model.EmployeesUpdateModel
import com.rezaaprilyas.devtest.domain.repository.EmployeesRepository
import com.rezaaprilyas.devtest.utils.dispatcher.DispatchersProvider
import com.rezaaprilyas.devtest.utils.state.ResultState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeesUseCase @Inject constructor(
    private val repository: EmployeesRepository,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getEmployeeList(): ResultState<List<EmployeesModel>> {
        return withContext(dispatchers.io) {
            repository.getEmployeeList()
        }
    }

    suspend fun deleteEmployee(employeeId: Int): ResultState<EmployeesDeleteModel> {
        return withContext(dispatchers.io) {
            repository.deleteEmployee(employeeId)
        }
    }

    suspend fun insertEmployee(request: EmployeesInsertRequest): ResultState<EmployeesInsertModel> {
        return withContext(dispatchers.io) {
            repository.insertEmployee(request)
        }
    }

    suspend fun updateEmployee(employeeId: Int, request: EmployeesUpdateRequest): ResultState<EmployeesUpdateModel> {
        return withContext(dispatchers.io) {
            repository.updateEmployee(employeeId, request)
        }
    }
}
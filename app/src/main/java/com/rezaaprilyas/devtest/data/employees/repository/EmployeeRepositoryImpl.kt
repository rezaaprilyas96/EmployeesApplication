package com.rezaaprilyas.devtest.data.employees.repository

import com.rezaaprilyas.devtest.data.employees.dataseource.EmployeesRemoteDataSource
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.data.employees.mapper.EmployeesMapper
import com.rezaaprilyas.devtest.domain.model.EmployeesDeleteModel
import com.rezaaprilyas.devtest.domain.model.EmployeesInsertModel
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.domain.model.EmployeesUpdateModel
import com.rezaaprilyas.devtest.domain.repository.EmployeesRepository
import com.rezaaprilyas.devtest.utils.state.ResultState
import java.net.UnknownHostException
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val remoteDataSource: EmployeesRemoteDataSource,
    private val mapper: EmployeesMapper,
) : EmployeesRepository {

    override suspend fun getEmployeeList(): ResultState<List<EmployeesModel>> {
        return try {
            val response = remoteDataSource.getListEmployees()
            return if (response.isSuccessful) {
                val results = response.body()?.data.orEmpty()
                val employeesList = mapper.mapResponseToListDomain(results)
                ResultState.Success(employeesList)
            } else {
                ResultState.Failure("${response.message()} ${response.code()}")
            }
        } catch (exception: UnknownHostException) {
            ResultState.Failure(exception.message.toString())
        }
    }

    override suspend fun deleteEmployee(employeeId: Int): ResultState<EmployeesDeleteModel> {
        return try {
            val response = remoteDataSource.deleteEmployee(employeeId)
            val result = response.body()
            return if (response.isSuccessful && result != null) {
                val results = mapper.mapResponseToDomainDelete(result)
                ResultState.Success(results)
            } else {
                ResultState.Failure("${response.message()} ${response.code()}")
            }
        } catch (exception: UnknownHostException) {
            ResultState.Failure(exception.message.toString())
        }
    }

    override suspend fun insertEmployee(request: EmployeesInsertRequest): ResultState<EmployeesInsertModel> {
        return try {
            val response = remoteDataSource.insertEmployee(request)
            val result = response.body()
            return if (response.isSuccessful && result != null) {
                val results = mapper.mapResponseToDomainInsert(result)
                ResultState.Success(results)
            } else {
                ResultState.Failure("${response.message()} ${response.code()}")
            }
        } catch (exception: UnknownHostException) {
            ResultState.Failure(exception.message.toString())
        }
    }

    override suspend fun updateEmployee(employeeId: Int, request: EmployeesUpdateRequest): ResultState<EmployeesUpdateModel> {
        return try {
            val response = remoteDataSource.updateEmployee(employeeId, request)
            val result = response.body()
            return if (response.isSuccessful && result != null) {
                val results = mapper.mapResponseToDomainUpdate(result)
                ResultState.Success(results)
            } else {
                ResultState.Failure("${response.message()} ${response.code()}")
            }
        } catch (exception: UnknownHostException) {
            ResultState.Failure(exception.message.toString())
        }
    }
}
package com.rezaaprilyas.devtest.data.employees.mapper

import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesDeleteResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesInsertDataResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesInsertResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesLitstDataResponse
import com.rezaaprilyas.devtest.data.employees.dataseource.response.EmployeesUpdateResponse
import com.rezaaprilyas.devtest.domain.model.EmployeesDeleteModel
import com.rezaaprilyas.devtest.domain.model.EmployeesInsertModel
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.domain.model.EmployeesUpdateModel

class EmployeesMapper {

    fun mapResponseToDomain(response: EmployeesLitstDataResponse): EmployeesModel {
        return with(response) {
            EmployeesModel(
                id = id.orEmpty(),
                name = employeeName.orEmpty(),
                salary = employeeSalary.orEmpty(),
                age = employeeAge.orEmpty()
            )
        }
    }

    fun mapResponseToDomainDelete(response: EmployeesDeleteResponse): EmployeesDeleteModel {
        return with(response) {
            EmployeesDeleteModel(
                status = status.orEmpty(),
                data = data.orEmpty(),
                message = message.orEmpty()
            )
        }
    }

    fun mapResponseToDomainInsert(response: EmployeesInsertResponse): EmployeesInsertModel {
        return with(response) {
            EmployeesInsertModel(
                id = data?.id.orEmpty(),
                name = data?.name.orEmpty(),
                salary = data?.salary.orEmpty(),
                age = data?.age.orEmpty(),
                status = status.orEmpty(),
                message = message.orEmpty()
            )
        }
    }

    fun mapResponseToDomainUpdate(response: EmployeesUpdateResponse): EmployeesUpdateModel {
        return with(response) {
            EmployeesUpdateModel(
                name = data?.name.orEmpty(),
                salary = data?.salary.orEmpty(),
                age = data?.age.orEmpty(),
                status = status.orEmpty(),
                message = message.orEmpty()
            )
        }
    }

    fun mapResponseToListDomain(listResponse: List<EmployeesLitstDataResponse>): List<EmployeesModel> {
        return listResponse.map(::mapResponseToDomain)
    }
}
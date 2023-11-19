package com.rezaaprilyas.devtest.presentation.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.domain.model.EmployeesDeleteModel
import com.rezaaprilyas.devtest.domain.model.EmployeesInsertModel
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.domain.model.EmployeesUpdateModel
import com.rezaaprilyas.devtest.domain.usecase.EmployeesUseCase
import com.rezaaprilyas.devtest.utils.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val employeesUseCase: EmployeesUseCase) : ViewModel() {

    var listEmployees = ArrayList<EmployeesModel>()
    var employeesInsertRequest = EmployeesInsertRequest()
    var employeesUpdateRequest = EmployeesUpdateRequest()
    var employeesObject: EmployeesModel? = null
    var positionItem: Int = 0
    var exit: Boolean = false
    var idEmployee = 0

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _employees = MutableLiveData<List<EmployeesModel>>()
    val employees: LiveData<List<EmployeesModel>>
        get() = _employees

    private val _deleteEmployees = MutableLiveData<EmployeesDeleteModel>()
    val deleteEmployees: LiveData<EmployeesDeleteModel>
        get() = _deleteEmployees

    private val _insertEmployees = MutableLiveData<EmployeesInsertModel>()
    val insertEmployees: LiveData<EmployeesInsertModel>
        get() = _insertEmployees

    private val _updateEmployees = MutableLiveData<EmployeesUpdateModel>()
    val updateEmployees: LiveData<EmployeesUpdateModel>
        get() = _updateEmployees

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getEmployees() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = employeesUseCase.getEmployeeList()
            _isLoading.value = false

            when (result) {
                is ResultState.Success -> _employees.value = result.data
                is ResultState.Failure -> _error.value = result.error
            }
        }
    }

    fun deleteEmployees() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = employeesUseCase.deleteEmployee(idEmployee)
            _isLoading.value = false

            when (result) {
                is ResultState.Success -> _deleteEmployees.value = result.data
                is ResultState.Failure -> _error.value = result.error
            }
        }
    }

    fun insertEmployees() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = employeesUseCase.insertEmployee(employeesInsertRequest)
            _isLoading.value = false

            when (result) {
                is ResultState.Success -> _insertEmployees.value = result.data
                is ResultState.Failure -> _error.value = result.error
            }
        }
    }

    fun updateEmployees() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = employeesUseCase.updateEmployee(idEmployee, employeesUpdateRequest)
            _isLoading.value = false

            when (result) {
                is ResultState.Success -> _updateEmployees.value = result.data
                is ResultState.Failure -> _error.value = result.error
            }
        }
    }
}
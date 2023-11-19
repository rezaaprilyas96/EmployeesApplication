package com.rezaaprilyas.devtest.domain.model

import java.io.Serializable

class EmployeesInsertModel(
    val id: String,
    val name: String,
    val salary: String,
    val age: String,
    val status: String,
    val message: String
) : Serializable
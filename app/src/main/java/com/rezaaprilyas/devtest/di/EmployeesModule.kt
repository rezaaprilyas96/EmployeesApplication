package com.rezaaprilyas.devtest.di

import com.rezaaprilyas.devtest.data.employees.dataseource.EmployeesRemoteDataSource
import com.rezaaprilyas.devtest.data.employees.dataseource.EmployeesRemoteDataSourceImpl
import com.rezaaprilyas.devtest.data.employees.mapper.EmployeesMapper
import com.rezaaprilyas.devtest.data.employees.repository.EmployeeRepositoryImpl
import com.rezaaprilyas.devtest.data.routes.ApiServices
import com.rezaaprilyas.devtest.domain.repository.EmployeesRepository
import com.rezaaprilyas.devtest.domain.usecase.EmployeesUseCase
import com.rezaaprilyas.devtest.utils.dispatcher.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class EmployeesModule {

    @Provides
    fun provideEmployeesRemoteDataSource(apiServices: ApiServices): EmployeesRemoteDataSource {
        return EmployeesRemoteDataSourceImpl(apiServices)
    }

    @Provides
    fun provideEmployeesMapper(): EmployeesMapper = EmployeesMapper()

    @Provides
    fun provideEmployeesRepository(
        remoteDataSource: EmployeesRemoteDataSource,
        mapper: EmployeesMapper
    ): EmployeesRepository {
        return EmployeeRepositoryImpl(
            remoteDataSource = remoteDataSource,
            mapper = mapper
        )
    }

    @Provides
    fun provideEmployeesUseCase(
        repository: EmployeesRepository,
        dispatchers: DispatchersProvider
    ) = EmployeesUseCase(repository, dispatchers)
}
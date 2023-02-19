package com.network.data.remote

import com.network.data.remote.config.ApiConfiguration
import com.network.data.remote.model.BaseResponseArrayFormat
import com.network.data.remote.model.BaseResponseObjectFormat
import com.network.model.Data
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET(ApiConfiguration.EMPLOYEE)
    fun employee(): Call<BaseResponseObjectFormat<Data>>

    @GET(ApiConfiguration.EMPLOYEE_LIST)
    fun employeeList(): Call<BaseResponseArrayFormat<Data>>
}
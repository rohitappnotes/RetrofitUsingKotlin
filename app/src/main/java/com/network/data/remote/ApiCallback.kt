package com.network.data.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallback<T> : Callback<T?> {

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        println("code : " + response.code())
        println("errorBody : " + response.errorBody())
        println("body : " + response.body())
        onSuccess(response)
    }

    override fun onFailure(call: Call<T?>, throwable: Throwable) {
        onError(call, throwable)
    }

    abstract fun onSuccess(response: Response<T?>)
    abstract fun onError(call: Call<T?>, t: Throwable?)
}
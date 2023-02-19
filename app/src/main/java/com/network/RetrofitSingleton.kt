package com.kotlinrxdemo.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Rahul on 3/3/2018.
 */
class RetrofitSingleton private constructor() {

    fun configRetrofit(): ApiInterface? {

        if (apiService == null) {
            synchronized(RetrofitSingleton::class.java) {
                retrofit = Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                        .client(configClient())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(AppGSonBuilder.getAutoValueGson()))
                        .build()

                apiService = retrofit!!.create<ApiInterface>(ApiInterface::class.java!!)
            }
        }

        return apiService
    }

    private fun configClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)

        builder.connectTimeout(3, TimeUnit.MINUTES)
        builder.readTimeout(3, TimeUnit.MINUTES)
        return builder.build()
    }

    companion object {

        private var retrofit: Retrofit? = null
        private var apiService: ApiInterface? = null
        private var retrofitSingleton: RetrofitSingleton? = null

        val instance: RetrofitSingleton
            get() {
                if (retrofitSingleton == null) {
                    synchronized(RetrofitSingleton::class.java) {
                        retrofitSingleton = RetrofitSingleton()
                    }
                }
                return this!!.retrofitSingleton!!
            }
    }
}

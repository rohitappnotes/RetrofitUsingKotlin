package com.network.data.remote

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.network.BaseApplication
import com.network.BuildConfig
import com.network.data.remote.cache.ProvideCacheInterceptor
import com.network.data.remote.cache.ProvideOfflineCacheInterceptor
import com.network.data.remote.config.ApiConfiguration
import com.network.ui.test.TestActivity
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiClient {

    private val mTag: String = TestActivity::class.java.simpleName

    private lateinit var mRetrofit: Retrofit

    fun getClient(baseUrl: String): Retrofit {
        mRetrofit = getRetrofitInstance(baseUrl)
        return mRetrofit
    }

    private fun getRetrofitInstance(baseUrl: String): Retrofit {
        val okHttpClientBuilder = getOkHttpClientBuilderInstance(BaseApplication.application)
        val okHttpClient = okHttpClientBuilder.build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val builder = Retrofit.Builder()
        builder.baseUrl(baseUrl)
        builder.client(okHttpClient)
        builder.addConverterFactory(GsonConverterFactory.create(gson))
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return builder.build()
    }

    private fun getOkHttpClientBuilderInstance(context: Context): OkHttpClient.Builder {

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.connectTimeout(ApiConfiguration.CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(ApiConfiguration.CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(ApiConfiguration.CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)

        val cache = Cache(context.cacheDir, ApiConfiguration.CUSTOM_OK_HTTP_CACHE_SIZE)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(ProvideOfflineCacheInterceptor(context)) // used if network off OR on
        okHttpClientBuilder.addNetworkInterceptor(ProvideCacheInterceptor(context)) // only used when network is on

        okHttpClientBuilder.addInterceptor(ProvideOfflineCacheInterceptor(context, ApiConfiguration.CUSTOM_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS))
        okHttpClientBuilder.addNetworkInterceptor(ProvideCacheInterceptor(context, ApiConfiguration.CUSTOM_CACHE_DURATION_WITH_NETWORK_IN_SECONDS))
        okHttpClientBuilder.cache(provideOkHttpCache(context))

        if (BuildConfig.DEBUG) {
            val interceptor: Interceptor = LoggingInterceptor.Builder().setLevel(Level.BASIC).log(Log.VERBOSE).build()
            okHttpClientBuilder.addInterceptor(interceptor)
        }

        return okHttpClientBuilder
    }

    private fun provideOkHttpCache(context: Context): Cache? {
        var cache: Cache? = null
        try {
            val httpCacheDirectory = File(context.cacheDir, ApiConfiguration.CUSTOM_OK_HTTP_CACHE_DIR_NAME)
            cache = Cache(httpCacheDirectory, ApiConfiguration.CUSTOM_OK_HTTP_CACHE_SIZE)
        } catch (e: Exception) {
            Log.e(mTag, "Could not create Cache!", e)
        }
        return cache
    }
}
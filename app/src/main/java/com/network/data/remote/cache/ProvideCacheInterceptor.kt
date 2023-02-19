package com.network.data.remote.cache

import android.content.Context
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import java.io.IOException

class ProvideCacheInterceptor : Interceptor {

    companion object {
        private val mTag = ProvideCacheInterceptor::class.java.simpleName

        var DEFAULT_CACHE_DURATION_WITH_NETWORK_IN_SECONDS  = 5
        val MAX_AGE                                         = DEFAULT_CACHE_DURATION_WITH_NETWORK_IN_SECONDS
        const val HEADER_CACHE_CONTROL                      = "Cache-Control"
        const val HEADER_PRAGMA                             = "Pragma"
    }

    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, cacheDurationWithNetworkInSeconds: Int) {
        this.context = context
        DEFAULT_CACHE_DURATION_WITH_NETWORK_IN_SECONDS = cacheDurationWithNetworkInSeconds
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE, TimeUnit.SECONDS)
            .build()
        return response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }
}
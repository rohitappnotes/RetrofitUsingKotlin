package com.network.data.remote.cache

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit


class ProvideOfflineCacheInterceptor : Interceptor {

    companion object {
        private val TAG = ProvideOfflineCacheInterceptor::class.java.simpleName

        var DEFAULT_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS   = 7 * 24 * 60 * 60
        val MAX_STALE                                           = DEFAULT_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS
        const val HEADER_CACHE_CONTROL                          = "Cache-Control"
        const val HEADER_PRAGMA                                 = "Pragma"
    }

    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, cacheDurationWithoutNetworkInSeconds: Int) {
        this.context = context
        DEFAULT_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS = cacheDurationWithoutNetworkInSeconds
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!isNetworkAvailable(context)) {
            val cacheControl = CacheControl.Builder()
                .maxStale(MAX_STALE, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }

        return chain.proceed(request)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        for (networkInfo in info) {
            if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

    fun isConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
                if (networkCapabilities != null) {
                    return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                }
            } else {
                val networkInfo = manager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
            }
        }
        return false
    }
}
package com.network.data.remote

import com.network.data.remote.ApiClient.getClient
import com.network.data.remote.config.ApiConfiguration

object ApiServiceGenerator {

    /**
     * Retrofit Service Generator class which initializes the calling ApiService
     *
     * @param serviceClass -  The Retrofit Service Interface class.
     */
    fun <S> createService(serviceClass: Class<S>): S {
        return getClient(ApiConfiguration.BASE_URL).create(serviceClass)
    }
}
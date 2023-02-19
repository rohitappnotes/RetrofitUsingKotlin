package com.network.data.remote.model;

import com.google.gson.annotations.SerializedName

data class BaseResponseObjectFormat<T>(
    @SerializedName("code") var code: Int,
    @SerializedName("success") var success: Boolean,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: T? = null
)
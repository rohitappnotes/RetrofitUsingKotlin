package com.network.data.remote.model;

import com.google.gson.annotations.SerializedName

data class BaseResponsePagingFormat<T>(
    @SerializedName("total_number_of_pages") var totalNumberOfPages: Int,
    @SerializedName("current_page_number") var currentPageNumber: Int,
    @SerializedName("total_number_of_items") var totalNumberOfItems: Int,
    @SerializedName("item_in_one_page") var itemInOnePage: Int,
    @SerializedName("code") var code: Int,
    @SerializedName("success") var success: Boolean,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: List<T>? = null
)
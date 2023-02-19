package com.network.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("job") var job: String,
    @SerializedName("salary") var salary: String,
    @SerializedName("profile_picture") var profilePicture: String
)

data class Department(
    val department_id: Int,
    val name: String,
    val description: String
)

data class Category(
    val category_id: Int,
    val name: String,
    val description: String,
    val department_id: Int
)

data class Product(
    val product_id: Int,
    val name: String,
    val description: String,
    val price: String,
    val discounted_price: String,
    val thumbnail: String
)
package com.example.myapplicationkotlin

import com.google.gson.annotations.SerializedName

data class MyDataItem(
    val body: String,
    val id: Int,
    val title: String,
    @SerializedName("userId")
    val userId: Int
)
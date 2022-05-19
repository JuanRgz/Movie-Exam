package com.study.mymovies.core

import com.google.gson.annotations.SerializedName

data class GenericError(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val message: String,
    val success: Boolean
)

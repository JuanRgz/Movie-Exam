package com.study.mymovies.core

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun Exception.handleException(): GenericError{
    val e = this
    return when(e){
        is HttpException -> {
            val errorResponseString = e.response()?.errorBody()?.string()
            try {
                Gson().fromJson(errorResponseString, GenericError::class.java)
            } catch (ex: Exception) {
                GenericError(499,"error al obtener la respuesta", false)
            }
        }
        is SocketTimeoutException -> {
            GenericError(408, "Tiempo de respuesta excedido", false)
        }
        else -> {
            GenericError(498, "Error desconocido", false)
        }
    }
}
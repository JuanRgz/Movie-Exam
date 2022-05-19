package com.study.mymovies.core

import androidx.lifecycle.MutableLiveData

data class GenericResponse<S, E>(
    var status: StatusEnum,
    var data: S? = null,
    var error: E? = null
): MutableLiveData<GenericResponse<S, E>>()
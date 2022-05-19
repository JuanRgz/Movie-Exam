package com.study.mymovies.core.location

import com.google.firebase.firestore.Exclude

data class UserLocation(
    @get:Exclude var idUser: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)

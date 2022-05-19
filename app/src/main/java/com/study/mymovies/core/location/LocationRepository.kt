package com.study.mymovies.core.location

import com.google.firebase.firestore.FirebaseFirestore

class LocationRepository {
    fun saveLocation(locationUser: UserLocation, response: (result: Boolean) -> Unit) {

        try {
            with(FirebaseFirestore.getInstance()) {
                collection("places").document()
                    .set(locationUser)
                    .addOnSuccessListener {
                        response(true)
                    }
                    .addOnFailureListener {
                        response(false)
                    }.addOnCanceledListener {
                        response(false)
                    }
            }
        } catch (e: Exception) {
            response(false)
        }
    }
}
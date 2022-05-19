package com.study.mymovies.core.location

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentChange
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

    fun getLocation(cleanMap: () -> Unit, response: (result: UserLocation) -> Unit) {

        val firestore = FirebaseFirestore.getInstance()
        val locationsUserRef = firestore.collection("places")

        locationsUserRef.addSnapshotListener { snapshots, error ->
            if (error == null)
            {
                cleanMap()
                for (snapshot in snapshots!!.documentChanges) {
                    val location = snapshot.document.toObject(UserLocation::class.java)
                    when (snapshot.type) {
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED -> {
                            response(location)
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }
}
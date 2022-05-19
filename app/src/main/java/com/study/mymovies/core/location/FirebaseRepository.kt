package com.study.mymovies.core.location

import android.net.Uri
import androidx.lifecycle.Observer
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class FirebaseRepository {
    companion object {
        const val PLACES = "places"
        const val IMAGES = "images"
    }

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val storage by lazy { FirebaseStorage.getInstance() }

    fun saveLocation(locationUser: UserLocation, response: (result: Boolean) -> Unit) {

        try {
            firestore.apply{

                collection(PLACES).document()
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

        firestore.apply{
            val locationsUserRef = collection(PLACES)

            locationsUserRef.addSnapshotListener { snapshots, error ->
                if (error == null) {
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

    fun saveImage(image: Uri, unit: (Boolean) -> Unit) {
        storage.apply {
            val file = reference.child((0..999999).random().toString())
            file.putFile(image).addOnCompleteListener {
                unit(it.isSuccessful)
            }
        }
    }

    fun getImages(image: File, unit: (success: Boolean) -> Unit) {
        storage.apply {
            val file = reference.child((0..999999).random().toString())
            file.putFile(Uri.fromFile(image)).addOnCompleteListener {
                unit(it.isSuccessful)
            }
        }
    }
}
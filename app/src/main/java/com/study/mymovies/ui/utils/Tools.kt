package com.study.mymovies.ui.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.ByteArrayOutputStream

fun Fragment.showToast(msg: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(requireContext(), msg, length).show()
}

fun Fragment.uriToBase64(uri: Uri): String {
    val stream = ByteArrayOutputStream()
    val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()

    return Base64.encodeToString(byteArray, Base64.DEFAULT) ?: ""
}

fun Fragment.base64ToUri(){

}

fun Activity.checkingPermission(): Boolean{
    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE)
    var success = true
    permissions.forEach { permission ->
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_DENIED){
            success = false
        }
    }
    return success
}

fun Activity.requestingPermission() {
    val permission = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)
    ActivityCompat.requestPermissions(this,
        permission,
        101);
}
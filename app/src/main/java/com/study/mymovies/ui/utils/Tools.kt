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
import com.study.mymovies.core.App
import java.io.*

fun Fragment.showToast(msg: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(requireContext(), msg, length).show()
}

fun Fragment.uriToBase64(uri: Uri): String {
    val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    val byteArray = outputStream.toByteArray()

    return Base64.encodeToString(byteArray, Base64.DEFAULT) ?: ""
}

fun base64ToUri(imageData: String): File? {
    val imgBytesData: ByteArray = Base64.decode(
        imageData,
        Base64.DEFAULT
    )

    val file: File = File.createTempFile("image", null, App.instance.cacheDir)
    val fileOutputStream: FileOutputStream
    try {
        fileOutputStream = FileOutputStream(file)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        return null
    }

    val bufferedOutputStream = BufferedOutputStream(
        fileOutputStream
    )
    try {
        bufferedOutputStream.write(imgBytesData)
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    } finally {
        try {
            bufferedOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return file
}

fun Activity.checkingPermission(): Boolean{
    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)
    var success = true
    permissions.forEach { permission ->
        if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED){
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
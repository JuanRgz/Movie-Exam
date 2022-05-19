package com.study.mymovies.ui.fragment.images

import android.app.Activity
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.study.mymovies.R
import com.study.mymovies.core.App
import com.study.mymovies.core.location.FirebaseRepository
import com.study.mymovies.ui.utils.ResponseDialog
import java.io.File

class UploadImageViewModel: ViewModel() {
    fun saveImage(uri: Uri, activity: Activity, callback: ()-> Unit) {
        FirebaseRepository().saveImage(uri){
            callback()
            if(it){
                ResponseDialog.showDialog(activity, App.instance.getString(R.string.save_image_success), success = true)
            } else{
                ResponseDialog.showDialog(activity, App.instance.getString(R.string.save_image_success_error))
            }
        }
    }

}
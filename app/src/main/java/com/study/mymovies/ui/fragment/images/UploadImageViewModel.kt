package com.study.mymovies.ui.fragment.images

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.study.mymovies.core.location.FirebaseRepository
import java.io.File

class UploadImageViewModel: ViewModel() {
    fun saveImage(uri: Uri) {
        FirebaseRepository().saveImage(uri)
    }

}
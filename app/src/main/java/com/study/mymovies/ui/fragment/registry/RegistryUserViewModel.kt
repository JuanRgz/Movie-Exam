package com.study.mymovies.ui.fragment.registry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.mymovies.core.App
import com.study.mymovies.ui.fragment.registry.data.model.UserModel
import com.study.mymovies.ui.utils.ResponseDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistryUserViewModel: ViewModel() {
    var imageString: String = ""
    fun saveUser(user: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            App.db.movieDao().insertUser(user)
        }
    }

    fun validateUser(user: UserModel): Boolean{
        user.apply {
            return fullName.isNotEmpty() && phone.validatePhone() && email.isNotEmpty() && address.isNotEmpty() && image.isNotEmpty()
        }
    }

    private fun String.validatePhone(): Boolean{
        return this.length == 10
    }
}
package com.study.mymovies.ui.fragment.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.mymovies.core.App
import com.study.mymovies.ui.fragment.registry.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel: ViewModel() {
    fun getUsers(unit: (list: List<UserModel>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = App.db.movieDao().getAllUsers()
            withContext(Dispatchers.Main){
                unit(result)
            }
        }
    }
}
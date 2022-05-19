package com.study.mymovies.ui.fragment.registry.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val employeeNumber: Int = 0,
    val fullName: String,
    val phone: String,
    val email: String,
    val address: String,
    var image: String
)
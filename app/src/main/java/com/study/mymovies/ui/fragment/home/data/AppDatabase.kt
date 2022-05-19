package com.study.mymovies.ui.fragment.home.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.study.mymovies.core.MovieDao
import com.study.mymovies.ui.fragment.home.data.model.MovieResponseModel
import com.study.mymovies.ui.fragment.registry.data.model.UserModel

@Database(entities = [MovieResponseModel::class, UserModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

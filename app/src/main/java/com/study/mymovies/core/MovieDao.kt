package com.study.mymovies.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.study.mymovies.ui.fragment.home.data.model.MovieResponseModel
import com.study.mymovies.ui.fragment.registry.data.model.UserModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<MovieResponseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResponseModel)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(movie: UserModel)
}
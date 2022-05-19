package com.study.mymovies.ui.fragment.home.data

import com.study.mymovies.core.*
import com.study.mymovies.ui.fragment.home.data.model.MovieListResponseModel
import com.study.mymovies.ui.fragment.home.data.model.MovieResponseModel
import com.study.mymovies.ui.utils.InternetConnection
import kotlinx.coroutines.delay
import retrofit2.HttpException

class MovieRepository {
    private val service = ApiClient.getRetrofitClient()
    suspend fun getPlayingMovies(observer: GenericResponse<MovieListResponseModel, GenericError>){
        try {
            delay(2000)
            if(InternetConnection.isInternetAvailable()){
                val responseData = service.getPlayingMovies()
                if(responseData.isSuccessful){
                    //saving movies in room
                    val response = responseData.body()
                    saveInRoom(response)
                    observer.postValue(GenericResponse(StatusEnum.SUCCESS, response))
                }else{
                    throw HttpException(responseData)
                }
            } else{
                val result = MovieListResponseModel(getFromRoom())
                observer.postValue(GenericResponse(StatusEnum.SUCCESS, result))
            }
        }catch (e: Exception){
            observer.postValue(GenericResponse(StatusEnum.ERROR, error = e.handleException()))
        }
    }

    suspend fun getPopularMovies(observer: GenericResponse<MovieListResponseModel, GenericError>){
        try {
            delay(2000)
            if(InternetConnection.isInternetAvailable()){
                val responseData = service.getPopularMovies()
                if(responseData.isSuccessful){
                    val response = responseData.body()
                    saveInRoom(response)
                    observer.postValue(GenericResponse(StatusEnum.SUCCESS, response))
                }else{
                    throw HttpException(responseData)
                }
            }else{
                val result = MovieListResponseModel(getFromRoom())
                observer.postValue(GenericResponse(StatusEnum.SUCCESS, result))
            }
        }catch (e: Exception){
            observer.postValue(GenericResponse(StatusEnum.ERROR, error = e.handleException()))
        }
    }

    suspend fun getTopRatedMovies(observer: GenericResponse<MovieListResponseModel, GenericError>){
        try {
            delay(2000)
            if(InternetConnection.isInternetAvailable()){
                val responseData = service.getTopRatedMovies()
                if(responseData.isSuccessful){
                    val response = responseData.body()
                    saveInRoom(response)
                    observer.postValue(GenericResponse(StatusEnum.SUCCESS, response))
                }else{
                    throw HttpException(responseData)
                }
            } else {
                val result = MovieListResponseModel(getFromRoom())
                observer.postValue(GenericResponse(StatusEnum.SUCCESS, result))
            }
        }catch (e: Exception){
            observer.postValue(GenericResponse(StatusEnum.ERROR, error = e.handleException()))
        }
    }

    private fun getFromRoom() : List<MovieResponseModel>{
        val room = App.db.movieDao()
        return room.getAllMovies()
    }

    private fun saveInRoom(response: MovieListResponseModel?) {
        val room = App.db.movieDao()
        response?.results?.forEach { movie ->
            room.insertMovie(movie)
        }
    }
}
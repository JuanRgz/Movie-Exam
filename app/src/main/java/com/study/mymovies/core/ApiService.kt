package com.study.mymovies.core

import com.study.mymovies.ui.fragment.home.data.model.MovieListResponseModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getPlayingMovies(@Query("api_key") key: String = ApiConstants.API_KEY, @Query("language") lang: String = ApiConstants.LANGUAGE): Response<MovieListResponseModel>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") key: String = ApiConstants.API_KEY, @Query("language") lang: String = ApiConstants.LANGUAGE): Response<MovieListResponseModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") key: String = ApiConstants.API_KEY, @Query("language") lang: String = ApiConstants.LANGUAGE): Response<MovieListResponseModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") key: String = ApiConstants.API_KEY, @Query("language") lang: String = ApiConstants.LANGUAGE): Response<MovieListResponseModel>

    @POST("movie/{movie_id}/rating")
    suspend fun postRateMovie(@Path("movie_id") idMovie: String): Any

    @DELETE("movie/{movie_id}/rating")
    suspend fun deleteRateMovie(@Path("movie_id") idMovie: String): Any

}
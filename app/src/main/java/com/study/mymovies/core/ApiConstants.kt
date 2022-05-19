package com.study.mymovies.core

object ApiConstants {
    val LANGUAGE: String = "es-MX"
    const val API_KEY = "ae66f6be8f2a839435eb91fdc7bf2919"
    const val PATH_IMG = "https://image.tmdb.org/t/p/w500/"
    const val BASE_URL = "https://api.themoviedb.org/3/"

    //ENDPOINT
    const val NOW_PLAYING = "movie/now_playing"
    const val POPULAR = "movie/popular"
    const val TOP_RATED = "movie/top_rated"
    const val UPCOMING = "movie/upcoming"
    const val RATE = "movie/{movie_id}/rating" //POST
    const val DELETE = "movie/{movie_id}/rating" //DELETE
}
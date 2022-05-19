package com.study.mymovies.core

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.study.mymovies.ui.fragment.home.data.AppDatabase

class App: Application() {
    companion object{
        lateinit var instance: App
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "movie_db"
        ).build()
        FirebaseApp.initializeApp(instance.applicationContext)
    }
}
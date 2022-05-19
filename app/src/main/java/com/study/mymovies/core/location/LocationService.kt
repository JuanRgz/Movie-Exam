package com.study.mymovies.core.location

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.location.LocationServices
import com.study.mymovies.R

class LocationService : Service() {
    var handler = Handler(Looper.getMainLooper())

    companion object {
        const val START = "START"
        const val STOP = "STOP"
        //multiplicamos milesimas por segundo por cantidad de minutos a definir en el intervalo
        const val INTERVAL: Long = 1000 * 60 * 15
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START -> {
                getLocation()
            }
            STOP -> {

            }
        }

        return START_STICKY
    }

    private fun getLocation() {
        handler.apply {
            val runnable = object : Runnable {
                override fun run() {
                    postDelayed(this, INTERVAL)
                    with(LocationRepository()) {
                        getUserLocation(applicationContext) {
                            Log.e("Location", it.toString())
                            if (it != null) {
                                saveLocation(
                                    UserLocation(
                                        latitud = it.latitude,
                                        longitud = it.longitude
                                    )
                                ) { result ->
                                    if (result) {
                                        createNotification(
                                            "Geolocalización",
                                            "Se subió correctamente"
                                        )
                                    } else {
                                        createNotification(
                                            "Geolocalización",
                                            "Falló al subir tu geolocalización"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            postDelayed(runnable, INTERVAL)
        }
    }

    @SuppressLint("MissingPermission")
    fun getUserLocation(context: Context, result: (location: Location?) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                result(location)
            }
        }
    }

    override fun onDestroy() {
        Log.d(this::class.java.simpleName, "Servicio destruido.")
        handler = Handler(Looper.getMainLooper())
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun createNotification(
        title: String,
        contentText: String,
        icon: Int = R.drawable.ic_map
    ) {
        val idUnique = (0..999).random()
        val channelID = getString(R.string.app_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelID, channelID, importance).apply {
                    description = channelID
                }

            val builder = NotificationCompat.Builder(applicationContext, channelID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(contentText)

            with(NotificationManagerCompat.from(applicationContext)) {

                (applicationContext
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                    createNotificationChannel(channel)
                }
                builder.setSilent(false)

                notify(idUnique, builder.build())
            }
        }
    }
}
package com.study.mymovies.ui.utils

import java.net.InetAddress

object InternetConnection {
    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("www.google.com")
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }
}
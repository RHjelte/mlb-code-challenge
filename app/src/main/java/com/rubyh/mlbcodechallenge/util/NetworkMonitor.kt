package com.rubyh.mlbcodechallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkMonitorA {
}

class NetworkMonitor(private val context: Context) {

    // For simplicity, check the ConnectivityManager network capabilities
    // for network availability.  In a real situation, the availability
    // should be monitored using NetworkCallback for Android 9 and above.

    fun isNetworkAvailable() : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return with (connectivityManager) {
            activeNetwork?.let {
                checkCapabilities(getNetworkCapabilities(it))
            } ?: false
        }
    }

    private fun checkCapabilities(capabilities: NetworkCapabilities?) : Boolean {
        return capabilities?.let {
            when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } ?: false

    }
}
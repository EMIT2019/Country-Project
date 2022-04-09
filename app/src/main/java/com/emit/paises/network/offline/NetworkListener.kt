package com.emit.paises.network.offline

import android.Manifest
import android.app.Application
import android.content.Context
import android.net.*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData

@RequiresApi(Build.VERSION_CODES.M)
class NetworkListener internal constructor(private val connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {

    private var lastValue: Boolean? = null

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_NETWORK_STATE])
    constructor(application: Application) : this(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            oneTimeNotify()
        }

        override fun onLost(network: Network) {
            oneTimeNotify()
        }
    }

    private fun oneTimeNotify() {
        val newValue = isInternetOn()

        if (lastValue != newValue) {
            postValue(newValue)
            lastValue = newValue
        }
    }

    override fun onActive() {
        super.onActive()
        oneTimeNotify()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_BLUETOOTH)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }

    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun isInternetAvailable(): Boolean {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    @RequiresPermission(android.Manifest.permission.INTERNET)
    private fun isInternetOn(): Boolean {
        if (isInternetAvailable()) {
            try {
                val p = Runtime.getRuntime().exec("ping -c 1 www.google.com")
                val value = p.waitFor()
                return value == 0
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return false
    }
}
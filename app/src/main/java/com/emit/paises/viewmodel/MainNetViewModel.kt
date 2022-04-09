package com.emit.paises.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.emit.paises.network.offline.NetworkListener

@RequiresApi(Build.VERSION_CODES.M)
class MainNetViewModel(application: Application): AndroidViewModel(application) {
    val connectivity: LiveData<Boolean>

    init {
        connectivity = NetworkListener(application)
    }

}
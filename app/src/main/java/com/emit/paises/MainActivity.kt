package com.emit.paises

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emit.paises.adapters.CountryListAdapter
import com.emit.paises.databinding.ActivityMainBinding
import com.emit.paises.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            if(networkStatus(this@MainActivity)){
                initObservers()
                binding.recyclerView.adapter = CountryListAdapter()
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }else {
                Log.d("No_internet", "This message is shown because the app doesn't have internet access!")
            }
        }catch (ex: Exception){
            Log.d("Exception", ex.toString())
        }
    }

    private fun initObservers(){
        viewModel.countries.observe(this) {
            (binding.recyclerView.adapter as CountryListAdapter).submitList(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun networkStatus(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        var wifiSignal: Boolean = false

        if(capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) wifiSignal = true
        Log.d("Network_Status", "${capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)}")
        return wifiSignal
    }
}
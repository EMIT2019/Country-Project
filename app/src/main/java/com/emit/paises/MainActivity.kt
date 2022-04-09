package com.emit.paises

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emit.paises.adapters.CountryListAdapter
import com.emit.paises.databinding.ActivityMainBinding
import com.emit.paises.viewmodel.MainNetViewModel
import com.emit.paises.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    private val netViewModel: MainNetViewModel by lazy {
        ViewModelProvider(this@MainActivity)[MainNetViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        binding.recyclerView.adapter = CountryListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun initObservers(){
        netViewModel.connectivity.observe(this, Observer {
            it?.run{
                if(it){
                    viewModel.countries.observe(this@MainActivity) {
                        (binding.recyclerView.adapter as CountryListAdapter).submitList(it)
                    }
                    Log.d("Network_Status", "Internet is On")
                }else {
                    Toast.makeText(this@MainActivity, "Se ha perdido la conexión a internet", Toast.LENGTH_SHORT).show()
                    Log.d("Network_Status", "Internet is Off")
                }
            }
        })
    }
}
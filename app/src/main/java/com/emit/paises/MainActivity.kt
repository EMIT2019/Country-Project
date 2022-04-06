package com.emit.paises

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emit.paises.adapters.CountryListAdapter
import com.emit.paises.databinding.ActivityMainBinding
import com.emit.paises.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        binding.recyclerView.adapter = CountryListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun initObservers(){
        viewModel.countries.observe(this) {
            (binding.recyclerView.adapter as CountryListAdapter).submitList(it)
        }
    }
}
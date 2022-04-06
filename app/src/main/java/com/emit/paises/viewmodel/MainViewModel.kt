package com.emit.paises.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emit.paises.model.entities.Country
import com.emit.paises.model.repository.CountryRepository
import com.emit.paises.model.repository.CountryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val countryRepository: CountryRepository = CountryRepositoryImpl.invoke()): ViewModel() {
    private var _countries = MutableLiveData<List<Country>>()
    var countries: LiveData<List<Country>> = _countries

    init {
        getCountries()
    }

    private fun getCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            countryRepository.getCountries()
                .collectLatest {
                    _countries.postValue(it)
                }
        }
    }
}
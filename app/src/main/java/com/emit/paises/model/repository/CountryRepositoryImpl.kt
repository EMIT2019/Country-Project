package com.emit.paises.model.repository

import com.emit.paises.model.entities.Country
import com.emit.paises.network.CountryApi
import com.emit.paises.network.services.CountryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl private constructor(
    private val countryService: CountryService,
    /*private val countryDao: CountryDao*/): CountryRepository {

    companion object {
        @Volatile
        private var INSTANCE : CountryRepository? = null

        private fun createInstance() : CountryRepository =
            CountryRepositoryImpl(CountryApi.countryService)

        operator fun invoke() : CountryRepository =
            INSTANCE ?: synchronized(this) {
                createInstance()
            }.also { INSTANCE = it }
    }


    override suspend fun getCountries(): Flow<List<Country>> =
        flow {
            emit(countryService.getCountries().data  )
        }

    /*override suspend fun insertCountry(country: Country) {
        flow {
            emit(countryDao.insert(country))
        }
    }*/
}
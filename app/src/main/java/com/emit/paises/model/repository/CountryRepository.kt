package com.emit.paises.model.repository

import com.emit.paises.model.entities.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getCountries() : Flow<List<Country>>

    //suspend fun insertCountry(country: Country)
}
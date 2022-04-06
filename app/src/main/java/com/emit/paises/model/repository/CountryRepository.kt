package com.emit.paises.model.repository

import kotlinx.coroutines.flow.Flow
import com.emit.paises.model.entities.Country

interface CountryRepository {
    suspend fun getCountries() : Flow<List<Country>>
}
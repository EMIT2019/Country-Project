package com.emit.paises.network.services

import com.emit.paises.network.response.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {
    @GET("countries")
    suspend fun getCountries(@Query("limit") limit: Int = 10): CountryResponse
}
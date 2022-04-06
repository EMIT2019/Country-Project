package com.emit.paises.network

import com.emit.paises.network.services.CountryService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryApi {
    private const val BASE_URL: String = "https://wft-geo-db.p.rapidapi.com/v1/geo/"
    private const val API_KEY:  String = "7e14bacc9fmsha1d5aa0a904cd92p13dc7djsn59a46178a72d"

    private val authenticationInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "7e14bacc9fmsha1d5aa0a904cd92p13dc7djsn59a46178a72d")
            .url(newUrl).build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder().addInterceptor(authenticationInterceptor).build()

    private val converter = GsonConverterFactory.create(
        GsonBuilder()
            .excludeFieldsWithModifiers()
            .create()
    )

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(converter)
        .build()

    val countryService = retrofit.create(CountryService::class.java)
}
package com.emit.paises.model.entities

import androidx.room.Entity

data class Country(
    val idCountry: Int,
    val code: String,
    val currencyCodes: List<String>,
    val name: String,
    val wikiDataId: String
)
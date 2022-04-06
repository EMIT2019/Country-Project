package com.emit.paises.model.entities

data class Country(
    val code: String,
    val currencyCodes: List<String>,
    val name: String,
    val wikiDataId: String
)
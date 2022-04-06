package com.emit.paises.network.response

import com.emit.paises.model.entities.Country
import com.emit.paises.model.entities.Link
import com.emit.paises.model.entities.Metadata

data class CountryResponse(
    val data: List<Country>,
    @Transient
    val links: List<Link>,
    @Transient
    val metadata: Metadata
)
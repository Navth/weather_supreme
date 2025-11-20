package org.breezyweather.sources.ipma.json

import kotlinx.serialization.Serializable

@Serializable
data class IpmaDistrictResult(
    val idRegiao: Int?,
    val idDistrito: Int?,
    val nome: String?,
)

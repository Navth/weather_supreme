package org.breezyweather.sources.ipma.json

import kotlinx.serialization.Serializable

@Serializable
data class IpmaLocationResult(
    val idRegiao: Int?,
    val idAreaAviso: String?,
    val globalIdLocal: Int?,
    val latitude: String,
    val idDistrito: Int?,
    val local: String?,
    val longitude: String,
)

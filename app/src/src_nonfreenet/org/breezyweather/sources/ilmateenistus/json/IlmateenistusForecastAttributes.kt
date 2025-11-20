package org.breezyweather.sources.ilmateenistus.json

import kotlinx.serialization.Serializable

@Serializable
data class IlmateenistusForecastAttributes(
    val from: String?,
    val className: String?,
    val value: String?,
    val deg: String?,
    val mps: String?,
)

package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.Serializable

@Serializable
data class LhmtLocationsResult(
    val code: String = "",
    val name: String? = null,
    val administrativeDivision: String? = null,
    val countryCode: String? = null,
    val coordinates: LhmtCoordinates,
)

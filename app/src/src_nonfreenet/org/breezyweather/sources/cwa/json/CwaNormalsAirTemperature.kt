package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaNormalsAirTemperature(
    val monthly: List<CwaNormalsMonthly>?,
)

package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsDailyProperties(
    val periods: List<NwsDailyPeriods>?,
)

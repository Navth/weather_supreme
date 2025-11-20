package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsCurrentValue(
    val value: Double?,
    val maxValue: Double?,
)

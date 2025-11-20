package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoCurrentPressure(
    val Value: String?,
)

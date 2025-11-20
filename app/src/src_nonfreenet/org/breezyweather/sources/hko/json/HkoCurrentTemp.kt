package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoCurrentTemp(
    val Value: String?,
    val DefaultStation: String?,
)

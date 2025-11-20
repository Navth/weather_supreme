package org.breezyweather.sources.ims.json

import kotlinx.serialization.Serializable

@Serializable
data class ImsWeatherCode(
    val id: String?,
    val desc: String?,
)

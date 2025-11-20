package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsValueWeatherContainer(
    val values: List<NwsValueWeather>?,
)

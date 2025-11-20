package org.breezyweather.sources.ims.json

import kotlinx.serialization.Serializable

@Serializable
data class ImsForecastData(
    val country: ImsCountry?,
    val daily: ImsDaily?,
    val hourly: Map<String, ImsHourly>?,
)

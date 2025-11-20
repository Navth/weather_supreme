package org.breezyweather.sources.mgm.json

import kotlinx.serialization.Serializable

@Serializable
data class MgmAlertWeather(
    val yellow: List<String>?,
    val orange: List<String>?,
    val red: List<String>?,
)

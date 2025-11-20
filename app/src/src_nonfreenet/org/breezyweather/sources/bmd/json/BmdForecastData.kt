package org.breezyweather.sources.bmd.json

import kotlinx.serialization.Serializable

@Serializable
data class BmdForecastData(
    val rf: List<BmdForecast>?,
    val temp: List<BmdForecast>?,
    val rh: List<BmdForecast>?,
    val windspd: List<BmdForecast>?,
    val winddir: List<BmdForecast>?,
    val cldcvr: List<BmdForecast>?,
    val windgust: List<BmdForecast>?,
)

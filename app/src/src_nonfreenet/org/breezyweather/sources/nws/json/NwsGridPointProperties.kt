package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsGridPointProperties(
    val temperature: NwsValueDoubleContainer?,
    val dewpoint: NwsValueDoubleContainer?,
    val relativeHumidity: NwsValueIntContainer?,
    val apparentTemperature: NwsValueDoubleContainer?,
    val wetBulbGlobeTemperature: NwsValueDoubleContainer?,
    val heatIndex: NwsValueDoubleContainer?,
    val windChill: NwsValueDoubleContainer?,
    val skyCover: NwsValueIntContainer?,
    val windDirection: NwsValueIntContainer?,
    val windSpeed: NwsValueDoubleContainer?,
    val windGust: NwsValueDoubleContainer?,
    val weather: NwsValueWeatherContainer?,
    val probabilityOfPrecipitation: NwsValueIntContainer?,
    val quantitativePrecipitation: NwsValueDoubleContainer?,
    val iceAccumulation: NwsValueDoubleContainer?,
    val snowfallAmount: NwsValueDoubleContainer?,
    val ceilingHeight: NwsValueDoubleContainer?,
    val visibility: NwsValueDoubleContainer?,
    val pressure: NwsValueDoubleContainer?,
    val probabilityOfThunder: NwsValueDoubleContainer?,
)

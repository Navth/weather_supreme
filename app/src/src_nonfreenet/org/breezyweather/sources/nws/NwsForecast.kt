package org.breezyweather.sources.nws

/**
 * Transition class for NwsGridPointProperties data
 */
class NwsForecast(
    val temperature: Double?,
    val dewpoint: Double?,
    val relativeHumidity: Int?,
    val apparentTemperature: Double?,
    val wetBulbGlobeTemperature: Double?,
    val heatIndex: Double?,
    val windChill: Double?,
    val skyCover: Int?,
    val windDirection: Int?,
    val windSpeed: Double?,
    val windGust: Double?,
    val weather: String?,
    val probabilityOfPrecipitation: Int?,
    val quantitativePrecipitation: Double?,
    val iceAccumulation: Double?,
    val snowfallAmount: Double?,
    val ceilingHeight: Double?,
    val visibility: Double?,
    val pressure: Double?,
    val probabilityOfThunder: Int?,
)

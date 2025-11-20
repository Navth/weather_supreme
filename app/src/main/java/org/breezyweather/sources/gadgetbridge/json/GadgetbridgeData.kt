package org.breezyweather.sources.gadgetbridge.json

import kotlinx.serialization.Serializable

/**
 * Refer to
 * https://codeberg.org/Freeyourgadget/Gadgetbridge/src/branch/master/app/src/main/java/nodomain/freeyourgadget/gadgetbridge/model/WeatherSpec.java
 */
@Serializable
data class GadgetbridgeData(
    val timestamp: Int? = null,
    val location: String? = null,
    val currentTemp: Int? = null,
    val currentConditionCode: Int? = null,
    val currentCondition: String? = null,
    val currentHumidity: Int? = null,
    val todayMaxTemp: Int? = null,
    val todayMinTemp: Int? = null,
    val windSpeed: Float? = null,
    val windDirection: Int? = null,
    val uvIndex: Float? = null,
    val precipProbability: Int? = null,
    val dewPoint: Int? = null,
    val pressure: Float? = null,
    val cloudCover: Int? = null,
    val visibility: Float? = null,
    val sunRise: Int? = null,
    val sunSet: Int? = null,
    val moonRise: Int? = null,
    val moonSet: Int? = null,
    val moonPhase: Int? = null,
    val feelsLikeTemp: Int? = null,
    val forecasts: List<GadgetbridgeDailyForecast>? = null,
    val hourly: List<GadgetbridgeHourlyForecast>? = null,
    val airQuality: GadgetbridgeAirQuality? = null,
)

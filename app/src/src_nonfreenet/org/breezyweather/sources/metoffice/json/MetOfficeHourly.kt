package org.breezyweather.sources.metoffice.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MetOfficeHourly(
    @Serializable(with = DateSerializer::class)
    val time: Date,
    val screenTemperature: Double?,
    val maxScreenAirTemp: Double?,
    val minScreenAirTemp: Double?,
    val screenDewPointTemperature: Double?,
    val feelsLikeTemperature: Double?,
    val windSpeed10m: Double?,
    val windDirectionFrom10m: Int?,
    val windGustSpeed10m: Double?,
    val max10mWindGust: Double?,
    val visibility: Int?,
    val screenRelativeHumidity: Double?,
    val mslp: Int?,
    val uvIndex: Int?,
    val significantWeatherCode: Int?,
    val precipitationRate: Double?,
    val totalPrecipAmount: Double?,
    val totalSnowAmount: Double?,
    val probOfPrecipitation: Int?,
)

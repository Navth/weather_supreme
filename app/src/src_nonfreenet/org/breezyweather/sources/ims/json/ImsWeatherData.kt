package org.breezyweather.sources.ims.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImsWeatherData(
    @SerialName("warnings_metadata") val warningsMetadata: ImsWarningsMetadata?,
    @SerialName("all_warnings") val allWarnings: Map<String, ImsWarning>?,
    val analysis: ImsAnalysis?,
    @SerialName("forecast_data") val forecastData: Map<String, ImsForecastData>?,
    @SerialName("wind_directions") val windDirections: Map<String, ImsWindDirection>?,
    @SerialName("weather_codes") val weatherCodes: Map<String, ImsWeatherCode>?,
)

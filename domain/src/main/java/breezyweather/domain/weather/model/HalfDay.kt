package breezyweather.domain.weather.model

import breezyweather.domain.weather.reference.WeatherCode
import breezyweather.domain.weather.wrappers.HalfDayWrapper
import java.io.Serializable

/**
 * Half day.
 */
data class HalfDay(
    /**
     * A short description of the weather condition
     */
    val weatherText: String? = null,

    /**
     * A long description of the weather condition. Used as a half-day summary
     */
    val weatherSummary: String? = null,
    val weatherCode: WeatherCode? = null,
    val temperature: Temperature? = null,
    val precipitation: Precipitation? = null,
    val precipitationProbability: PrecipitationProbability? = null,
    val precipitationDuration: PrecipitationDuration? = null,
    val wind: Wind? = null,
) : Serializable {

    fun toHalfDayWrapper() = HalfDayWrapper(
        weatherText = this.weatherText,
        weatherSummary = this.weatherSummary,
        weatherCode = this.weatherCode,
        temperature = this.temperature?.toTemperatureWrapper(),
        precipitation = this.precipitation,
        precipitationProbability = this.precipitationProbability,
        precipitationDuration = this.precipitationDuration,
        wind = this.wind
    )
}

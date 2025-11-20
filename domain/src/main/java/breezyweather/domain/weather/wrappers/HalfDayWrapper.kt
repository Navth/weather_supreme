package breezyweather.domain.weather.wrappers

import breezyweather.domain.weather.model.HalfDay
import breezyweather.domain.weather.model.Precipitation
import breezyweather.domain.weather.model.PrecipitationDuration
import breezyweather.domain.weather.model.PrecipitationProbability
import breezyweather.domain.weather.model.Wind
import breezyweather.domain.weather.reference.WeatherCode

/**
 * Half day.
 */
data class HalfDayWrapper(
    /**
     * A short description of the weather condition
     */
    val weatherText: String? = null,

    /**
     * A long description of the weather condition. Used as a half-day summary
     */
    val weatherSummary: String? = null,
    val weatherCode: WeatherCode? = null,
    val temperature: TemperatureWrapper? = null,
    val precipitation: Precipitation? = null,
    val precipitationProbability: PrecipitationProbability? = null,
    val precipitationDuration: PrecipitationDuration? = null,
    val wind: Wind? = null,
) {
    fun toHalfDay() = HalfDay(
        weatherText = this.weatherText,
        weatherSummary = this.weatherSummary,
        weatherCode = this.weatherCode,
        temperature = this.temperature?.toTemperature(),
        precipitation = this.precipitation,
        precipitationProbability = this.precipitationProbability,
        precipitationDuration = this.precipitationDuration,
        wind = this.wind
    )
}

package breezyweather.domain.weather.wrappers

import org.breezyweather.unit.temperature.Temperature

/**
 * Temperature.
 */
data class TemperatureWrapper(
    val temperature: Temperature? = null,
    val feelsLike: Temperature? = null,
) {
    fun toTemperature(
        computedApparent: Temperature? = null,
        computedWindChill: Temperature? = null,
        computedHumidex: Temperature? = null,
    ) = breezyweather.domain.weather.model.Temperature(
        temperature = this.temperature,
        sourceFeelsLike = this.feelsLike,
        computedApparent = computedApparent,
        computedWindChill = computedWindChill,
        computedHumidex = computedHumidex
    )
}

package breezyweather.domain.weather.model

import breezyweather.domain.weather.wrappers.TemperatureWrapper
import java.io.Serializable

/**
 * Temperature.
 */
data class Temperature(
    val temperature: org.breezyweather.unit.temperature.Temperature? = null,
    val sourceFeelsLike: org.breezyweather.unit.temperature.Temperature? = null,
    val computedApparent: org.breezyweather.unit.temperature.Temperature? = null,
    val computedWindChill: org.breezyweather.unit.temperature.Temperature? = null,
    val computedHumidex: org.breezyweather.unit.temperature.Temperature? = null,
) : Serializable {

    val feelsLikeTemperature: org.breezyweather.unit.temperature.Temperature? = sourceFeelsLike
        ?: computedApparent
        ?: computedWindChill
        ?: computedHumidex

    fun toTemperatureWrapper() = TemperatureWrapper(
        temperature = this.temperature,
        feelsLike = this.sourceFeelsLike
    )
}

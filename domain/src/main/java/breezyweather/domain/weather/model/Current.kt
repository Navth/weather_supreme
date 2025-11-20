package breezyweather.domain.weather.model

import breezyweather.domain.weather.reference.WeatherCode
import breezyweather.domain.weather.wrappers.CurrentWrapper
import org.breezyweather.unit.distance.Distance
import org.breezyweather.unit.pressure.Pressure
import org.breezyweather.unit.ratio.Ratio
import java.io.Serializable

/**
 * Current.
 */
data class Current(
    val weatherText: String? = null,
    val weatherCode: WeatherCode? = null,
    val temperature: Temperature? = null,
    val wind: Wind? = null,
    val uV: UV? = null,
    val airQuality: AirQuality? = null,
    val relativeHumidity: Ratio? = null,
    val dewPoint: org.breezyweather.unit.temperature.Temperature? = null,
    /**
     * Pressure at sea level
     * Use Kotlin extensions to initialize this value, like 1013.25.hectopascals
     */
    val pressure: Pressure? = null,
    val cloudCover: Ratio? = null,
    val visibility: Distance? = null,
    val ceiling: Distance? = null,
    val dailyForecast: String? = null,
    // Is actually a description of the nowcast
    val hourlyForecast: String? = null,
) : Serializable {

    fun toCurrentWrapper() = CurrentWrapper(
        weatherText = this.weatherText,
        weatherCode = this.weatherCode,
        temperature = this.temperature?.toTemperatureWrapper(),
        wind = this.wind,
        uV = uV ?: this.uV,
        relativeHumidity = this.relativeHumidity,
        dewPoint = this.dewPoint,
        pressure = this.pressure,
        cloudCover = this.cloudCover,
        visibility = this.visibility,
        ceiling = this.ceiling,
        dailyForecast = this.dailyForecast,
        hourlyForecast = this.hourlyForecast
    )
}

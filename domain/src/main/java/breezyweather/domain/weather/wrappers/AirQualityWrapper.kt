package breezyweather.domain.weather.wrappers

import breezyweather.domain.weather.model.AirQuality
import java.util.Date

data class AirQualityWrapper(
    val current: AirQuality? = null,
    val dailyForecast: Map<Date, AirQuality>? = null,
    val hourlyForecast: Map<Date, AirQuality>? = null,
)

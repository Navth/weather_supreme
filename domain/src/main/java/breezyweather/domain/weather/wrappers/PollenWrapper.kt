package breezyweather.domain.weather.wrappers

import breezyweather.domain.weather.model.Pollen
import java.util.Date

data class PollenWrapper(
    /**
     * Current will be used as "Today" if dailyForecast and hourlyForecast are empty
     */
    val current: Pollen? = null,
    val dailyForecast: Map<Date, Pollen>? = null,
    val hourlyForecast: Map<Date, Pollen>? = null,
)

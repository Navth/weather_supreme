package breezyweather.domain.weather.model

import java.io.Serializable
import kotlin.time.Duration

/**
 * Precipitation duration.
 */
data class PrecipitationDuration(
    val total: Duration? = null,
    val thunderstorm: Duration? = null,
    val rain: Duration? = null,
    val snow: Duration? = null,
    val ice: Duration? = null,
) : Serializable

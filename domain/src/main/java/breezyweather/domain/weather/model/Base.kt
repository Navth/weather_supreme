package breezyweather.domain.weather.model

import java.io.Serializable
import java.util.Date

/**
 * Base.
 */
data class Base(
    // val publishDate: Date = Date(),
    val refreshTime: Date? = null,
    val forecastUpdateTime: Date? = null,
    val currentUpdateTime: Date? = null,
    val airQualityUpdateTime: Date? = null,
    val pollenUpdateTime: Date? = null,
    val minutelyUpdateTime: Date? = null,
    val alertsUpdateTime: Date? = null,
    val normalsUpdateTime: Date? = null,
    val normalsUpdateLatitude: Double = 0.0,
    val normalsUpdateLongitude: Double = 0.0,
) : Serializable
